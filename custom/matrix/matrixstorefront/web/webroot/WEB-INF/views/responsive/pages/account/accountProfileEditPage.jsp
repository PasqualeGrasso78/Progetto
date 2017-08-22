<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="account-section-header">
    <div class="row">
        <div class="container-lg col-md-6">
            <spring:theme code="text.account.profile.updatePersonalDetails"/>
        </div>
    </div>
</div>

<div class="row">
    <div class="container-lg col-md-6">
        <div class="account-section-content">
            <div class="account-section-form">
                <form:form action="update-profile" method="post" commandName="updateProfileForm">

                   <formElement:formSelectBox idKey="profile.title" labelKey="profile.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="form.select.empty" items="${titleData}" selectCSSClass="form-control"/>
                    <formElement:formInputBox idKey="profile.firstName" labelKey="profile.firstName" path="firstName" inputCSS="text" mandatory="true"/>
                    <formElement:formInputBox idKey="profile.lastName" labelKey="profile.lastName" path="lastName" inputCSS="text" mandatory="true"/>

                    <div class="row">
                        <div class="col-sm-6 col-sm-push-6">
                            <div class="accountActions">
                                <ycommerce:testId code="personalDetails_savePersonalDetails_button">
                                    <button type="submit" class="btn btn-primary btn-block">
                                        <spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/>
                                    </button>
                                </ycommerce:testId>
                            </div>
                        </div>
                      
                        
                        <div class="col-sm-6 col-sm-pull-6">
                            <div class="accountActions">
                                <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
                                    <button type="button" class="btn btn-default btn-block backToHome">
                                        <spring:theme code="text.account.profile.cancel" text="Cancel"/>
                                    </button>
                                </ycommerce:testId>
                           </div>
                        </div>       
                </form:form>
			  </div>
			
			 </div>           
            </div>
        </div>
   </div>  

<div class="form-group">
				<label class="control-label " for="address.firstName">
			ADD ADDRESS</label>            

<div class="back-link border">
    <div class="row">
        <div class="container-lg col-md-6">
            <span class="label">${headline}</span>
        </div>
    </div>
</div>


<div class="row">
    <div class="container-lg col-md-6">
        <div class="account-section-content">
            <div class="account-section-form">
                
			<form id="addressForm" action="/matrixstorefront/en/my-account/add-address" method="post">
				<input id="addressId" name="addressId" class="add_edit_delivery_address_id" type="hidden" value="">
				<input type="hidden" name="bill_state" id="address.billstate">
			
				<div id="countrySelector" data-address-code="" data-country-iso-code="" class="form-group">
					<div class="form-group">
						<label class="control-label " for="address.country">
						Country<span class="mandatory">
								</span>
						<span class="skip">
								</span>
					</label>
					<div class="control">
						<select id="address.country" name="countryIso" class="form-control"><option value="" disabled="disabled" selected="selected">
							Country</option>
							<option value="GG">Guernsey</option><option value="IM">Isle of Man</option><option value="JE">Jersey</option><option value="GB">United Kingdom</option></select></div>
					</div>
				
				    </div>
				    <div id="i18nAddressForm" class="i18nAddressForm">

		           <div class="form-group">
					<label class="control-label " for="address.title">
					Title<span class="mandatory">
							</span>
					<span class="skip">
							</span>
					</label>
			<div class="control">
					<select id="address.title" name="titleCode" class="form-control"><option value="" disabled="disabled" selected="selected">
							title</option>
						<option value="mr">Mr.</option><option value="mrs">Mrs.</option><option value="miss">Miss</option><option value="ms">Ms.</option><option value="dr">Dr.</option><option value="rev">Rev.</option></select></div>
					</div>
		
			<div class="form-group">
					<label class="control-label " for="address.firstName">
				First Name</label>
				
			<input id="address.firstName" name="firstName" class="form-control form-control" type="text" value=""></div>
			
			<div class="form-group">
					<label class="control-label " for="address.surname">
				Last Name</label>
				
			<input id="address.surname" name="lastName" class="form-control form-control" type="text" value=""></div>
			
			<div class="form-group">
					<label class="control-label " for="address.line1">
				Address Line 1</label>
				
			<input id="address.line1" name="line1" class="form-control form-control" type="text" value=""></div>
			
			<div class="form-group">
					<label class="control-label " for="address.line2">
				Address Line 2<span>&nbsp;(optional)</span>
				</label>
				
			<input id="address.line2" name="line2" class="form-control form-control" type="text" value=""></div>
			
			<div class="form-group">
					<label class="control-label " for="address.townCity">
				City</label>
				
			<input id="address.townCity" name="townCity" class="form-control form-control" type="text" value=""></div>
			
			<div class="form-group">
					<label class="control-label " for="address.postcode">
				Post Code</label>
				
			<input id="address.postcode" name="postcode" class="form-control form-control" type="text" value=""></div>
			
	        <div class="form-group">
					<label class="control-label " for="address.phone">
				Phone number<span>&nbsp;(optional)</span>
				</label>
				
			<input id="address.phone" name="phone" class="form-control form-control" type="text" value=""></div>

			<div>
			<input type="hidden" name="CSRFToken" value="518e9047-6a1f-47c7-9257-5d975f3dbdd4">
			</div></div>
		
			<div class="checkbox">
				    <div class="form-group">
				<div class="checkbox">
	   		
	   		<label class="control-label add-address-left-label" for="defaultAddress">
	   			<input id="defaultAddress" name="defaultAddress" class="add-address-left-input" type="checkbox" value="true"><input type="hidden" name="_defaultAddress" value="on">Make this my default address<span class="mandatory">
	   					</span>
	   			<span class="skip"></span>
	   		</label>
	   	</div>

</div>
</div>
		
		<div id="addressform_button_panel" class="form-actions">
					<div class="accountActions">
						<div class="row">
							<div class="col-sm-6 col-sm-push-6 accountButtons">
									<button class="btn btn-primary btn-block change_address_button show_processing_message" type="submit">
										Save
									</button>
							</div>
							<div class="col-sm-6 col-sm-pull-6 accountButtons">
									<a class="btn btn-block btn-default" href="../my-account/update-profile">
										Cancel
									</a>
								
							</div>
						</div>
					</div>
				
			
		</div>
	<div>
<input type="hidden" name="CSRFToken" value="518e9047-6a1f-47c7-9257-5d975f3dbdd4">
</div></form>
</div>
        </div>
    </div>
</div>
