ACC.checkoutnote = {

	spinner: $("<img src='" + ACC.config.commonResourcePath + "/images/spinner.gif' />"),
	noteCODE: '',

	showNoteBook: function ()
	{
		$(document).on("click", "#viewAddressBook", function ()
		{
			var data = $("#savedAddressListHolder").html();
			$.colorbox({

				height: false,
				html: data,
				onComplete: function ()
				{

					$(this).colorbox.resize();
				}
			});

		})
	},

	showRemoveAddressConfirmation: function ()
	{
		$(document).on("click", ".removeAddressButton", function ()
		{
			var noteCODE = $(this).data("noteCODE");
			$.colorbox({
				inline: true,
				height: false,
				href: "#popup_confirm_address_removal_" + noteCODE,
				onComplete: function ()
				{

					$(this).colorbox.resize();
				}
			});

		})
	}
}

// Address Verification
$(document).ready(function ()
{
	with (ACC.checkoutnote)
	{

		showNoteBook();
		showRemoveAddressConfirmation();
	}
});


