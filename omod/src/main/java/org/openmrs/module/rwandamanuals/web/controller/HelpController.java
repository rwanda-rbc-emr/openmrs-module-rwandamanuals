/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.rwandamanuals.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.rwandamanuals.Constants;
import org.openmrs.module.rwandamanuals.Manuals;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Manual list controller
 */
@Controller("rwandaManualsHelpController")
@RequestMapping("/help")
public class HelpController {
	
	protected static final Log log = LogFactory.getLog(HelpController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String display(ModelMap model) {
		
		AdministrationService svc = Context.getAdministrationService();
			
		model.put("supportPhone", svc.getGlobalProperty(Constants.MODULE_ID + ".supportPhone"));
		model.put("supportEmail", svc.getGlobalProperty(Constants.MODULE_ID + ".supportEmail"));
		model.put("manuals", Manuals.getManuals());
		
		return "/module/rwandamanuals/help";
	}
}
