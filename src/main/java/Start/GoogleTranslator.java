package Start;

import java.util.Arrays;

import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;





public class GoogleTranslator
{
	public static String Translate(String Source, String Language)
	{
		// Set your API-Key from https://console.developers.google.com/
		String googleCode = GUI.googleCodeTextField.getText();
		String applicationName = "Translator";
		String Results = "";

		try
		{
			Translate tTranslator = new Translate.Builder(GoogleNetHttpTransport.newTrustedTransport(),GsonFactory.getDefaultInstance(), null).setApplicationName(applicationName).build();
			
			//Translate tTranslator = new Translate.Builder(com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport(), com.google.api.client.json.gson.GsonFactory.getDefaultInstance(), null).setApplicationName(applicationName).build();
			Translate.Translations.List ttList = tTranslator.new Translations().list(Arrays.asList(Source), Language);
			ttList.setKey(googleCode);
			TranslationsListResponse tlrResponse = ttList.execute();
			for (TranslationsResource tlrResource : tlrResponse.getTranslations())
			{
				Results = Results + tlrResource.getTranslatedText();
			}

		} catch (Exception e)
		{
			System.err.println(e);
		}
		return Results;
	}
}
