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

package org.openmrs.module.rwandamanuals;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;

/**
 * Enumerates module manuals
 */
public class Manuals {
	
	protected static final Log log = LogFactory.getLog(Manuals.class);
	
	/**
	 * Gets all the manuals embedded in this module
	 * @return map of module names to manuals
	 */
	public static Map<String, String> getManuals() {
		Map<String, String> manuals = new TreeMap<String, String>();
		
		try {
			for (String path : getResourceListing(Constants.MANUALS_DIR)) {
				String filename = path.substring(path.lastIndexOf('/') + 1);
				String[] tokens = filename.split("\\.");
				String moduleId = tokens.length > 0 ? tokens[0] : null;			
				
				Module module = ModuleFactory.getModuleById(moduleId);
				if (module != null) {
					String name = module.getName();
					manuals.put(name, filename);
				}
				else {
					log.warn("No module found for manual: " + moduleId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return manuals;
	}
	
	/**
	 * Lists resources in this modules JAR
	 * @param path the internal path within the JAR
	 * @return the 
	 * @throws IOException if error occurs
	 */
	private static List<String> getResourceListing(String path) throws IOException {
		CodeSource src = Manuals.class.getProtectionDomain().getCodeSource();
		List<String> list = new ArrayList<String>();

		if (src != null) {
			URL jar = src.getLocation();
			ZipInputStream zip = new ZipInputStream(jar.openStream());
			ZipEntry ze = null;

			while ((ze = zip.getNextEntry()) != null) {
				String entryName = ze.getName();
				if (entryName.startsWith(path) && !ze.isDirectory()) {
					list.add(entryName);
				}
			}
		}
		return list;
	}
}
