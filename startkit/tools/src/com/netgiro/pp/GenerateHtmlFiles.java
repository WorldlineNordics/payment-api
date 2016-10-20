package com.netgiro.pp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.netgiro.pp.util.resourcebundle.ExternalResourceBundleControl;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class GenerateHtmlFiles {

	private static final String CONTEXT_DIR = "/context/";
	private static final String TEMPLATE_DIR = "/template/";

	static Map<String,String> IBP_DATA = getDummyIbpData();
	static Map<String,String> DDDS_DATA = getDummyDDDSData();
	static Map<String,String> INPUT_DATA = getDummyInputData();
	static Map<String,String> PREV_DATA = getDummyPrevData();
	static Map<String,String> REQ_DATA = getDummyReqData();
	static Map<String,String> RES_DATA = getDummyResultData();

	public static void main( String[] args ) throws Exception {
		//		genOne("SEPAo_payout_start.template","strings.properties");
		genAll();
	}

	private static void genAll() throws Exception {
		File templDir = new File(GenerateHtmlFiles.class.getResource(TEMPLATE_DIR).getFile());
		File localeDir = new File(GenerateHtmlFiles.class.getResource(CONTEXT_DIR).getFile());
		assert(templDir.isDirectory());
		assert(localeDir.isDirectory());
		String[] templateFiles = templDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".template");
			}
		});
		String[] localeFiles = localeDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("strings") && name.endsWith("properties");
			}
		});
		for ( String templ : templateFiles ) {
			System.err.println("generating all lang/country combos from template: "+templ);
			for (String locFile : localeFiles) {
				Locale loc = getLocaleForLangFile(locFile);
				genHtmlFromTemplate(new File(templ), loc);
			}
		}
	}

	private static void genOne(String templFile, String stringsFile) throws Exception {
		File templ = new File(GenerateHtmlFiles.class.getResource(TEMPLATE_DIR+templFile).getFile());
		File locale = new File(GenerateHtmlFiles.class.getResource(CONTEXT_DIR+stringsFile).getFile());
		Locale loc = getLocaleForLangFile(locale.getName());
		genHtmlFromTemplate(templ, loc);
	}

	static File genHtmlFromTemplate( File templ, Locale locale ) throws Exception {
		Configuration cfg = new Configuration();

		File f = new File(GenerateHtmlFiles.class.getResource("/template").getFile());
		cfg.setDirectoryForTemplateLoading(f);
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template temp = cfg.getTemplate(templ.getName(),
				locale );

		File res = getFreshFile(templ,locale);

		Object rootMap = createRootMap(locale);

		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(res), "UTF-8");

		temp.process(rootMap, writer);
		writer.flush();
		writer.close();
		return res;
	}

	private static File getFreshFile(File templ, Locale locale) throws IOException {
		String genBase = "./gen_html/";
		String fname = genBase+getGoodDir(genBase, locale)+getGenFileName(templ,locale);
		File f = new File(fname);
		if ( f.exists() )
			if ( !f.delete() )
				throw new IOException("unable to delete file: "+fname);
		return f;
	}

	private static String getGoodDir( String baseDir, Locale locale ) {
		String locDir = StringUtils.isBlank( locale.getLanguage() ) ? "default/" : locale.getLanguage()+"/";
		locDir += StringUtils.isBlank( locale.getCountry() ) ? "" : locale.getCountry()+"/";
		File f = new File(baseDir+locDir);
		if ( !f.exists() )
			f.mkdirs();
		return locDir;
	}

	private static String getGenFileName(File templ, Locale locale) {
		String lang = StringUtils.isBlank( locale.getLanguage() ) ? "" : "_"+locale.getLanguage();
		String country = StringUtils.isBlank( locale.getCountry() ) ? "" : "_"+locale.getCountry();
		return StringUtils.removeEnd(templ.getName(), ".template")
				+ lang + country + ".html";
	}

	private static Object createRootMap( Locale locale ) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("static", getStaticData( locale ) );
		res.put("config", getDummyConfigData(locale) );
		res.put("req", REQ_DATA );
		res.put("input", INPUT_DATA );
		res.put("prev", PREV_DATA );
		res.put("ddds", DDDS_DATA );
		res.put("result", RES_DATA );
		res.put("ibp", IBP_DATA );
		return res;
	}
	private static Map<String,String> getStaticData( Locale locale ) {
		ResourceBundle.Control ctrl = new ExternalResourceBundleControl(
				ResourceBundle2Txt.RESOURCE_FOLDER);
		ResourceBundle bundle = ResourceBundle.getBundle(
				ResourceBundle2Txt.RESOURCE_NAME, locale, ctrl );
		Map<String,String> statics = new HashMap<String, String>();
		for (String key : bundle.keySet() ) {
			statics.put(key.replaceFirst("static.", ""), bundle.getString(key));
		}
		return statics;
	}

	private static Locale getLocaleForLangFile(String langFileName ) {
		String langCountry = StringUtils.removeEnd(
				StringUtils.removeStart(langFileName, "strings"), ".properties" );
		if ( StringUtils.isBlank(langCountry) )
			return Locale.ROOT; //FIXME?!
		String[] split = StringUtils.split(langCountry,"_");
		if (split.length==1)
			return new Locale( split[0] );
		return new Locale( split[0], split[1] );
	}

	private static Map<String, String> getDummyIbpData() {
		Map<String, String> res = new HashMap<String, String>();
		res.put("bankUrl", "bankUrl");
		res.put("bankMethod", "meth");
		res.put("bankForm", "form");
		return res;
	}

	private static Map<String, String> getDummyResultData() {
		Map<String, String> res = new HashMap<String, String>();
		res.put("paymentReference", "123");
		res.put("dueDate", "12-12-12");
		res.put("formattedDueDate", "12-12-12");
		res.put("paymentMethodId", "3000");//FR=2024,GB=2023
		res.put("encryptedResponse", "enc resp");
		res.put("encryptedResponseUrl", "encUrl");
		res.put("transactionStatus", "OK!");
		res.put("paymentSlipUrl", "http://www.slipUrl");
		res.put("type", "EFT");
		return res;
	}

	private static Map<String, String> getDummyDDDSData() {
		Map<String, String> res = new HashMap<String, String>();
		res.put("acsUrl", "123");
		res.put("paReq", "123456");
		res.put("termUrl", "term");
		res.put("md", "md");
		return res;
	}

	private static Map<String, String> getDummyPrevData() {
		Map<String, String> res = new HashMap<String, String>();
		res.put("cardType", "visa");
		res.put("cardNumber", "123456");
		res.put("expDateMonth", "12");
		res.put("expDateYear", "13");
		res.put("startDateMonth", "12");
		res.put("startDateYear", "12");
		res.put("issue", "issue");
		return res;
	}

	private static Map<String, String> getDummyInputData() {
		Map<String,String> res = new HashMap<String, String>();
		res.put("paymentType", "CARD");
		res.put("cardNumberField", "1234");
		res.put("cvCodeField", "123");
		res.put("cardType", "VISA");
		res.put("expDateMonth", "12");
		res.put("issue", "2");
		res.put("expDateYear", "12");
		res.put("cvCode", "123");
		res.put("cardNumber", "123456789123456");
		res.put("bankId", "4711");
		res.put("paymentMethodId", "4711");
		res.put("bankAccountNr", "666");
		res.put("bankBranchNr", "777");
		res.put("bankAccountIban", "SE123456789");
		res.put("bankCity", "Stockholm");
		res.put("bankName", "SHB");
		res.put("bankSortCode", "");
		res.put("bankStreetName", "Odengatan");
		res.put("bankSwiftBIC", "SESHBSS");
		res.put("bankZipCode", "14711");
		res.put("bankAccountCheckNr", "");
		res.put("encryptedCustomerRedirect", "XXXX");
		res.put("encryptedStatusTicket", "YYYYY");
		res.put("billingFirstName", "Kalle");
		res.put("billingLastName", "Karlberg");
		res.put("startDateMonth", "12");
		res.put("startDateYear", "12");
		return res;
	}

	private static Map<String, String> getDummyReqData() {
		Map<String,String> res = new HashMap<String, String>();
		res.put("formattedAmount", "100.00");
		res.put("currency", "EUR");
		res.put("orderId", "OrderId");
		res.put("orderDescription", "OrderDescr");
		res.put("encryptedCustomerRedirect", "XXXX");
		res.put("encryptedStatusTicket", "YYYYY");
		res.put("billingFirstName", "Kalle");
		res.put("billingLastName", "Karlberg");
		res.put("dueDate", "20121213");
		res.put("billingEmail", "niklas@dr.se");
		res.put("billingAddressLine1", "Textilgatan 31 billing");
		res.put("billingAddressLine2", "Second address line billing");
		res.put("billingCity", "Stockholm billing");
		res.put("billingZipCode", "144 80");
		res.put("billingCountryCode", "SE");
		res.put("mandateId", "123");
		res.put("billingFullName", "Niklas Pragsten");
		res.put("billingAddress1", "Textilgatan 31 billingn");
		res.put("billingAddress2", "Second address line billing");
		res.put("bankSwiftBIC", "WERTSEDF");
		res.put("bankAccountIban", "DE00000123456789");
		res.put("mandateDate", "20140220");
		res.put("shippingFirstName", "Niklas");
		return res;
	}

	private static Map<String,String> getDummyConfigData(Locale locale) {
		Map<String,String> res = new HashMap<String, String>();
		if ( StringUtils.isNotBlank( locale.getCountry() ) ) {
			res.put("content_url", "../../../WebContent/");
		} else {
			res.put("content_url", "../../WebContent/");
		}
		res.put("payment_servlet", "DUMMY");
		res.put("refund_servlet", "REFUND");
		res.put("ibp_servlet", "DUMMY");
		res.put("dd_servlet", "DUMMY");
		res.put("cancel_servlet", "DUMMY");
		res.put("eft_servlet", "DUMMY");
		res.put("open_invoice_servlet", "DUMMY");
		return res;
	}
}
