package draziw.karavan.grainkingdom.supportclass;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

public class mySpannableString extends SpannableString{

	public mySpannableString(CharSequence source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public void setSpanToLine(Object what,int baseline,int start, int end, int flags) {		
		String[] splitedtextarray = this.toString().split("\n");
		if (baseline>splitedtextarray.length) baseline=splitedtextarray.length;
		
		int charcount=0;
		for (int linecount=1;linecount<baseline;linecount++) {
			charcount+=splitedtextarray[linecount-1].length();
			charcount+=1;// to \n - simbol
		}
		
		int theend=0;
		if (end==0) {
			theend=charcount+splitedtextarray[baseline-1].length();
		} else {
			theend=Math.min(charcount+end,charcount+splitedtextarray[baseline-1].length());
		}
		
		this.setSpan(what, charcount+start, theend, flags);			
	}

	//tag open [color=FFCEBF] , close= [/color]
	public static SpannableString setColorByTags(SpannableString tekSpannedStr) {
		String[] splitedtextarray = tekSpannedStr.toString().split("[color=");
		int comulativeLength=0;
		ForegroundColorSpan[] tekSpan=null;
		for (String tek: splitedtextarray) {
			comulativeLength+=tek.length();
			int closeIdx = tek.indexOf("]");
			tekSpan = tekSpannedStr.getSpans(comulativeLength, comulativeLength+closeIdx,ForegroundColorSpan.class);
			
		}
				
		
		if (tekSpan!=null) {
			tekSpannedStr.removeSpan(tekSpan);
		}
		
		return tekSpannedStr;				
	}
	
}
