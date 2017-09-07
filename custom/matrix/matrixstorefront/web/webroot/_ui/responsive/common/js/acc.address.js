ACC.address = {
		
		_autoload : [ "addNoteConfirmation","bindToChangeAddressButton", "bindCreateUpdateAddressForm",
			"bindSuggestedDeliveryAddresses",
			"bindCountrySpecificAddressForms", "showAddressFormButtonPanel",
			"bindViewAddressBook", "bindToColorboxClose",
			 "showAddNoteFromBookConfirmation",
			"showRemoveAddressFromBookConfirmation",
			"showRemoveNoteFromBookConfirmation", "removeNoteConfirmation",
			"backToListAddresses", "removePopupNote" ],

	spinner : $("<img src='" + ACC.config.commonResourcePath
			+ "/images/spinner.gif' />"),
	addressID : '',

	handleChangeAddressButtonClick : function() {

		ACC.address.addressID = ($(this).data("address")) ? $(this).data(
				"address") : '';
		$('#summaryDeliveryAddressFormContainer').show();
		$('#summaryOverlayViewAddressBook').show();
		$('#summaryDeliveryAddressBook').hide();

		$.getJSON(getDeliveryAddressesUrl, ACC.address.handleAddressDataLoad);
		return false;
	},

	handleAddressDataLoad : function(data) {
		ACC.address.setupDeliveryAddressPopupForm(data);

		// Show the delivery address popup
		ACC.colorbox.open("", {
			inline : true,
			href : "#summaryDeliveryAddressOverlay",
			overlayClose : false,
			onOpen : function() {
				// empty address form fields
				ACC.address.emptyAddressForm();
				$(document).on('change', '#saveAddress', function() {
					var saveAddressChecked = $(this).prop('checked');
					$('#defaultAddress').prop('disabled', !saveAddressChecked);
					if (!saveAddressChecked) {
						$('#defaultAddress').prop('checked', false);
					}
				});
			}
		});

	},

	setupDeliveryAddressPopupForm : function(data) {
		// Fill the available delivery addresses
		$('#summaryDeliveryAddressBook').html(
				$('#deliveryAddressesTemplate').tmpl({
					addresses : data
				}));
		// Handle selection of address
		$('#summaryDeliveryAddressBook button.use_address').click(
				ACC.address.handleSelectExistingAddressClick);
		// Handle edit address
		$('#summaryDeliveryAddressBook button.edit').click(
				ACC.address.handleEditAddressClick);
		// Handle set default address
		$('#summaryDeliveryAddressBook button.default').click(
				ACC.address.handleDefaultAddressClick);
	},

	emptyAddressForm : function() {
		var options = {
			url : getDeliveryAddressFormUrl,
			data : {
				addressId : ACC.address.addressID,
				createUpdateStatus : ''
			},
			type : 'GET',
			success : function(data) {
				$('#summaryDeliveryAddressFormContainer').html(data);
				ACC.address.bindCreateUpdateAddressForm();
			}
		};

		$.ajax(options);
	},

	handleSelectExistingAddressClick : function() {
		var addressId = $(this).attr('data-address');
		$.postJSON(setDeliveryAddressUrl, {
			addressId : addressId
		}, ACC.address.handleSelectExitingAddressSuccess);
		return false;
	},

	handleEditAddressClick : function() {

		$('#summaryDeliveryAddressFormContainer').show();
		$('#summaryOverlayViewAddressBook').show();
		$('#summaryDeliveryAddressBook').hide();

		var addressId = $(this).attr('data-address');
		var options = {
			url : getDeliveryAddressFormUrl,
			data : {
				addressId : addressId,
				createUpdateStatus : ''
			},
			target : '#summaryDeliveryAddressFormContainer',
			type : 'GET',
			success : function(data) {
				ACC.address.bindCreateUpdateAddressForm();
				ACC.colorbox.resize();
			},
			error : function(xht, textStatus, ex) {
				alert("Failed to update cart. Error details [" + xht + ", "
						+ textStatus + ", " + ex + "]");
			}
		};

		$(this).ajaxSubmit(options);
		return false;
	},

	handleDefaultAddressClick : function() {
		var addressId = $(this).attr('data-address');
		var options = {
			url : setDefaultAddressUrl,
			data : {
				addressId : addressId
			},
			type : 'GET',
			success : function(data) {
				ACC.address.setupDeliveryAddressPopupForm(data);
			},
			error : function(xht, textStatus, ex) {
				alert("Failed to update address book. Error details [" + xht
						+ ", " + textStatus + ", " + ex + "]");
			}
		};

		$(this).ajaxSubmit(options);
		return false;
	},

	handleSelectExitingAddressSuccess : function(data) {
		if (data != null) {
			ACC.refresh.refreshPage(data);
			ACC.colorbox.close();
		} else {
			alert("Failed to set delivery address");
		}
	},

	bindCreateUpdateAddressForm : function() {
		$('.create_update_address_form')
				.each(
						function() {
							var options = {
								type : 'POST',
								beforeSubmit : function() {
									$('#checkout_delivery_address').block({
										message : ACC.address.spinner
									});
								},
								success : function(data) {
									$('#summaryDeliveryAddressFormContainer')
											.html(data);
									var status = $('.create_update_address_id')
											.attr('status');
									if (status != null
											&& "success" === status
													.toLowerCase()) {
										ACC.refresh
												.getCheckoutCartDataAndRefreshPage();
										ACC.colorbox.close();
									} else {
										ACC.address
												.bindCreateUpdateAddressForm();
										ACC.colorbox.resize();
									}
								},
								error : function(xht, textStatus, ex) {
									alert("Failed to update cart. Error details ["
											+ xht
											+ ", "
											+ textStatus
											+ ", "
											+ ex + "]");
								},
								complete : function() {
									$('#checkout_delivery_address').unblock();
								}
							};

							$(this).ajaxForm(options);
						});
	},

	refreshDeliveryAddressSection : function(data) {
		$('.summaryDeliveryAddress').replaceWith(
				$('#deliveryAddressSummaryTemplate').tmpl(data));

	},

	bindSuggestedDeliveryAddresses : function() {
		var status = $('.add_edit_delivery_address_id').attr('status');
		if (status != null && "hasSuggestedAddresses" == status) {
			ACC.address.showSuggestedAddressesPopup();
		}
	},

	showSuggestedAddressesPopup : function() {

		ACC.colorbox.open("", {
			href : "#popup_suggested_delivery_addresses",
			inline : true,
			overlayClose : false,
			width : 525,
		});
	},

	bindCountrySpecificAddressForms : function() {
		$(document).on(
				"change",
				'#countrySelector select',
				function() {
					var options = {
						'addressCode' : '',
						'countryIsoCode' : $(this).val()
					};
					ACC.address.displayCountrySpecificAddressForm(options,
							ACC.address.showAddressFormButtonPanel);
				})

	},

	showAddressFormButtonPanel : function() {
		if ($('#countrySelector :input').val() !== '') {
			$('#addressform_button_panel').show();
		}
	},

	bindToColorboxClose : function() {
		$(document).on("click", ".closeColorBox", function() {
			ACC.colorbox.close();
		})
	},

	displayCountrySpecificAddressForm : function(options, callback) {
		$.ajax({
			url : ACC.config.encodedContextPath + '/my-account/addressform',
			async : true,
			data : options,
			dataType : "html",
			beforeSend : function() {
				$("#i18nAddressForm").html(ACC.address.spinner);
			}
		}).done(function(data) {
			$("#i18nAddressForm").html($(data).html());
			if (typeof callback == 'function') {
				callback.call();
			}
		});
	},

	bindToChangeAddressButton : function() {
		$(document).on("click", '.summaryDeliveryAddress .editButton',
				ACC.address.handleChangeAddressButtonClick);
	},

	bindViewAddressBook : function() {

		$(document).on("click", ".js-address-book", function(e) {
			e.preventDefault();

			ACC.colorbox.open("Saved Addresses", {
				href : "#addressbook",
				inline : true,
				width : "380px"
			});

		})

		$(document).on("click", '#summaryOverlayViewAddressBook', function() {
			$('#summaryDeliveryAddressFormContainer').hide();
			$('#summaryOverlayViewAddressBook').hide();
			$('#summaryDeliveryAddressBook').show();
			ACC.colorbox.resize();
		});
	},

	showRemoveAddressFromBookConfirmation : function() {
		$(document).on("click", ".removeAddressFromBookButton", function() {
			var addressId = $(this).data("addressId");
			var popupTitle = $(this).data("popupTitle");

			ACC.colorbox.open(popupTitle, {
				inline : true,
				height : false,
				href : "#popup_confirm_address_removal_" + addressId,
				onComplete : function() {

					$(this).colorbox.resize();
				}
			});

		})
	},
	showRemoveNoteFromBookConfirmation : function() {
		$(document).on("click", ".removeNoteFromBookButton", function() {
			var addressId = $(this).data("addressId");
			var popupTitle = $(this).data("popupTitle");

			ACC.colorbox.open(popupTitle, {
				inline : true,
				height : false,
				href : "#popup_confirm_note_removal_" + addressId,
				onComplete : function() {

					$(this).colorbox.resize();
				}
			});

		})
	},

	removeNoteConfirmation : function() {
		$(document).on(
				"click",
				".removeNoteButton",
				function() {
					var noteCode = $(this).data("addressId");
					var popupTitle = $(this).data("popupTitle");
					$.ajax({
						url : ACC.config.encodedContextPath
								+ '/my-account/remove-note?noteCode='
								+ noteCode,
						async : true,
						type : 'GET',
						success : function(data) {
							ACC.colorbox.open(popupTitle, {
								inline : true,
								height : false,
								overlayClose:false,
								href : "#popup_success_note_removal_"
										+ noteCode,
								onComplete : function() {

									$(this).colorbox.resize();
									$("button#cboxClose").hide();
								}
							});
						},
						error : function() {
							location.reload();
						}

					});

				})
	},
	removePopupNote : function() {
		$(document).on("click", ".closePopupNoteButton", function() {

			location.reload();
		});
	},

	backToListAddresses : function() {
		$(".addressBackBtn").on("click", function() {
			var sUrl = $(this).data("backToAddresses");
			window.location = sUrl;
		});
	},
	
	//agg

	showAddNoteFromBookConfirmation : function() {
		$(document).on("click", ".addNoteFromBookButton", function() {
			//var addressId = $(this).data("addressId");
			var popupTitle = $(this).data("popupTitle");

			ACC.colorbox.open(popupTitle, {
				inline : true,
				height : false,
				href : "#popup_confirm_note_add_",
				onComplete : function() {

					$(this).colorbox.resize();
				}
			});

		})
	},

	addNoteConfirmation : function() {
		$(document).on(
				"click",
				".addNoteButton",
				function() {
					var popupTitle = $(this).data("popupTitle");
					$.ajax({
						url : ACC.config.encodedContextPath
								+ '/my-account/remove-note?noteCode='
								+ noteCode,
						async : true,
						type : 'POST',
						success : function(data) {
							ACC.colorbox.open(popupTitle, {
								inline : true,
								height : false,
								href : "#popup_success_note_removal_"
										+ noteCode,
								onComplete : function() {

									$(this).colorbox.resize();
								}
							});
						},
						error : function() {
							location.reload();
						}

					});

				})
	},
	
	//agg

	showAddNoteFromBookConfirmation : function() {
		$(document).on("click", ".addNoteFromBookButton", function() {
			//var addressId = $(this).data("addressId");
			var popupTitle = $(this).data("popupTitle");

			ACC.colorbox.open(popupTitle, {
				inline : true,
				height : false,
				href : "#popup_confirm_note_add_",
				onComplete : function() {

					$(this).colorbox.resize();
				}
			});

		})
	},

	addNoteConfirmation : function() {
		$(document).on(
				"click",
				".addNoteButton",
				function() {
					
					var popupTitle = $(this).data("popupTitle");
					var description = $("#noteArea").val();
					var isShadow = $("#checkboxShadow").val();
					if(description!=''){
						
						$.ajax({
							
							url : ACC.config.encodedContextPath
									+ '/my-account/addnewnote',
							data: {description:description,
								   isShadow:isShadow},
							type : 'POST',
							success : function(data) {
								
								ACC.colorbox.open(popupTitle, {
									inline : true,
									height : false,
									overlayClose: false,
									href : "#popup_success_note_add_",
									onComplete : function() {

										$(this).colorbox.resize();
										$("button#cboxClose").hide();
									}
								});
							},
							error : function() {
								
								location.reload();
							}
						  });
						}else{
							
							/*ACC.colorbox.open(popupTitle, {
								inline : true,
								height : false,
								overlayClose: false,
								href : "#popup_insuccess_note_add_",
								onComplete : function() {

									$(this).colorbox.resize();
									$("button#cboxClose").hide();*/
								}
							});
							
						}

				})
	}

};

$(document).ready(function(){
	if( $('input[type="checkbox"]').is(':checked')) {
	       $("#buttonAddNote").show();
	   } else {
	       $("#buttonAddNote").hide();
	   }
	 
	$('input[type="checkbox"]').click(function() {
	   if( $(this).is(':checked')) {
	       $("#buttonAddNote").show();
	   } else {
	       $("#buttonAddNote").hide();
	   }
	});
	});
