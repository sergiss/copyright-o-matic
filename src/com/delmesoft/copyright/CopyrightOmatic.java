/* 
 * Copyright (c) 2021, Sergio S.- sergi.ss4@gmail.com http://sergiosoriano.com
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.delmesoft.copyright;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;

public class CopyrightOmatic {

	public static int addCopyright(File path, FileFilter filter, String copyright) throws Exception {
		int[] n = { 0 };
		_addCopyright(path, filter, copyright, n);
		return n[0];
	}
	
	private static void _addCopyright(File path, FileFilter filter, String copyright, int[] count) throws Exception {
		
		File[] files = path.listFiles(filter);
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {

					StringBuilder sb = new StringBuilder();
					sb.append(copyright);
					sb.append("\n");
					try (BufferedReader br = new BufferedReader(new FileReader(file))) {
						String line;
						while ((line = br.readLine()) != null) {
							sb.append(line).append("\n");
						}
					}
					
					try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
					writer.write(sb.toString()); }
					count[0]++;
					 
				} else {
					_addCopyright(file, filter, copyright, count);
				}
				
			}
		}	

	}
	
	

	public static class FileFilter implements FilenameFilter {

		private boolean subdirectories = true;
		private String[] extensions;
		private boolean matchCase;
		
		public FileFilter() {}
		
		public FileFilter(boolean subdirectories, boolean matchCase, String...extensions) {
			this.subdirectories = subdirectories;
			this.matchCase = matchCase;
			this.extensions = extensions;
		}

		@Override
		public boolean accept(File dir, String name) {
		
			if (extensions != null && extensions.length > 0) {
				if (!matchCase) {
					name = name.toLowerCase();
					for(String extension : extensions) {
						extension = extension.toLowerCase();
						if(name.endsWith(extension)) return true;
					}
				} else {
					for(String extension : extensions) {
						if(name.endsWith(extension)) return true;
					}
				}
				return subdirectories && new File(dir, name).isDirectory();
			}
			return true;
		}

		public boolean isSubdirectories() {
			return subdirectories;
		}

		public void setSubdirectories(boolean subdirectories) {
			this.subdirectories = subdirectories;
		}

		public String[] getExtensions() {
			return extensions;
		}

		public void setExtensions(String[] extensions) {
			this.extensions = extensions;
		}

		public boolean isMatchCase() {
			return matchCase;
		}

		public void setMatchCase(boolean matchCase) {
			this.matchCase = matchCase;
		}
				
	}

}
