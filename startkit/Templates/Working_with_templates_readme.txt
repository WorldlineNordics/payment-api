=================================
= GEN HTML
=================================
To generate html from templates
1. Update/add templates to your liking. If you want to add a result template 
	for market XY: add a file called: XY_result.template
1.1 OPTIONAL: remove content in gen_htm dir
2. Run generate by running: 
		gradlew runGenHtml (or gradlew runGen if your more lazy)
3. See results under build/gen_html (orginized by language/country)
4. NOTE: that you will get all possible language/country combinations for each template
5. NOTE: All data populated by the tool is dummy data EXCEPT data extracted from strings_<X>.properties
	i.e. stuff marked with ${static.<X>} on .template


=================================
= Modify transalations
=================================
1. Open Translation.xls in Microsoft Excel
2. Do your modifications
3. Save your modifications
4. Also save a text file, Save as... Translations_x-UTF-16LE-BOM.txt with the format Unicode text
5. Run 
		gradlew runTxtToResourceBundle (or runT if your lazy)
6. Diff changes
7. Commit your changes

