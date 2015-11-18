package net.uch.cursoLibre.logicreport;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import java.awt.Color;

public class UtilJasper {
	
	public static Style createStyleOrientBorderBackgFont(HorizontalAlign orientacion,Border border,Color color,int tam){
                 Style headerStyle = new Style();
                 headerStyle.setFont(new Font(tam, Font._FONT_VERDANA, true));
                 headerStyle.setBorder(border);
                 headerStyle.setHorizontalAlign(orientacion);
                 headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
                 headerStyle.setBackgroundColor(color);
                 headerStyle.setTransparency(Transparency.OPAQUE);
		return headerStyle;
	}
        
        public static Style createStyleOrientacion(HorizontalAlign orientacion,int font){
		Style headerStyle = new Style();
		headerStyle.setHorizontalAlign(orientacion);
                if(font!=0)
                headerStyle.setFont(new Font(9, Font._FONT_VERDANA, true));
		return headerStyle;
	}

        public static Style createStyleOrientacionAndBorder(HorizontalAlign orientacion,Border border){
		
             Style headerStyle = new Style();
             headerStyle.setBorder(border);
             headerStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
             headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
             headerStyle.setTransparency(Transparency.OPAQUE);
             return headerStyle;
	}



}
