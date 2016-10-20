To generate html from templates
1. Update/add templates to your liking. If you want to add a result template 
	for market XY: add a file called: XY_result.template
1.1 OPTIONAL: remove content in gen_htm dir
2. Run generate by running class: com.netgiro.pp.GenerateHtmlFiles as a java application
3. See results (orginized by language/country) under gen_html
4. NOTE: that you will get all possible language/country combinations for each template
5. NOTE: All data populated by the tool is dummy data EXCEPT data extracted from strings_<X>.properties
	i.e. stuff marked with ${static.<X>} on .template


To modify translations

1. Open Translation.xls in Microsux Excel
2. Do your modifications
3. Save your modifications
4. Also save a text file, Save as... Translations_x-UTF-16LE-BOM.txt with the format Unicode text
5. Compile Txt2ResourceBundle.java (javac Txt2ResourceBundle.java)
6. Run Txt2ResourceBundle (java Txt2ResourceBundle)
7. Run gradlew runTxtToResourceBundle command to generate properties files
8. Diff changes
8. Commit and push changes (Also commit auto generated properties file)
9. Build zip file with Templates and WebContent

