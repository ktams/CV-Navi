/*
 * DecoderFilter.java
 *
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ktams
 */
public class DecoderFilter extends FileFilter {
    
    public int Decoder;

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        if(ext != null)
        {
            switch(Decoder)
            {
                case c.LD_G30:
                    if (ext.equals("ld30"))
                        return true;
                    break;

                case c.LD_G31:
                    if (ext.equals("ld31"))
                        return true;
                    break;

                case c.LD_G32:
                case c.LD_W32:
                    if (ext.equals("ld32"))
                        return true;
                    break;

                case c.LD_G32_2:
                case c.LD_W32_2:
                    if (ext.equals("ld32_2"))
                        return true;
                    break;

                case c.FD_R:
                    if (ext.equals("fdr"))
                        return true;
                    break;

                case c.FD_R2:
                    if (ext.equals("fdr2"))
                        return true;
                    break;

                case c.LD_W33:
                case c.LD_G33:
                    if (ext.equals("ld33"))
                        return true;
                    break;

                case c.LD_G34:
                    if (ext.equals("ld34"))
                        return true;
                    break;

                case c.LD_G31Plus:
                case c.LD_G33Plus:
                case c.LD_G34Plus:
                    if (ext.equals("30p"))
                        return true;
                    break;

                case c.LD_G36Plus:
                    if (ext.equals("36p"))
                        return true;
                    break;

                case c.FD_M:
                    if (ext.equals("fdm"))
                        return true;
                    break;

                case c.FD_R_ex:
                    if (ext.equals("fde"))
                        return true;
                    break;

                case c.FD_XL:
                    if (ext.equals("fdxl"))
                        return true;
                    break;

                case c.FD_LED:
                    if (ext.equals("fdled"))
                        return true;
                    break;

                case c.WIB_30:
                    if (ext.equals("wib"))
                        return true;
                    break;

                case c.KENN:
                    if (ext.equals("kenn"))
                        return true;
                    break;

                case c.HEX:
                    if (ext.equals("hex"))
                        return true;
                    break;

                case c.TXT:
                    if (ext.equals("txt"))
                        return true;
                    break;

                case c.M3:
                    if (ext.equals("m3"))
                        return true;
                    break;

                case c.MC:
                    if (ext.equals("mc"))
                        return true;
                    if (ext.equals("conf"))
                        return true;
                    if (ext.equals("txt"))
                        return true;
                    break;

                case c.MC_WR:
                    if (ext.equals("mc"))
                        return true;
                    break;

            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        switch(Decoder)
        {
            case c.LD_G30:
                return "LD-G-30 (*.ld30)";

            case c.LD_G31:
                return "LD-G-31 (*.ld31)";

            case c.LD_G32:
                return "LD-G-32 (*.ld32)";

            case c.LD_G32_2:
                return "LD-G-32.2 (*.ld32_2)";

            case c.LD_W32:
                return "LD-W-32 (*.ld32)";

            case c.LD_W32_2:
                return "LD-W-32.2 (*.ld32_2)";

            case c.FD_R:
                return "FD-R basic (*.fdr)";

            case c.FD_R2:
                return "FD-R basic 2 (*.fdr2)";

            case c.LD_W33:
                return "LD-W-33 (*.ld33)";

            case c.LD_G33:
                return "LD-G-33 (*.ld33)";

            case c.LD_G34:
                return "LD-G-34 (*.ld34)";

            case c.LD_G31Plus:
                return "LD-G-31plus (*.30p)";

            case c.LD_G33Plus:
                return "LD-G-33plus (*.30p)";

            case c.LD_G34Plus:
                return "LD-G-34plus (*.30p)";

            case c.LD_G36Plus:
                return "LD-G-36plus (*.36p)";

            case c.FD_M:
                return "FD-M (*.fdm)";

            case c.FD_R_ex:
                return "FD-R extended (*.fde)";

            case c.FD_XL:
                return "FD-XL (*.fdxl)";

            case c.FD_LED:
                return "FD-LED (*.fdled)";

            case c.WIB_30:
                return "WIB-31/32/33 (*.wib)";

            case c.KENN:
                return "Custom (*.kenn)";

            case c.HEX:
                return "EasyControl Update (*.hex)";

            case c.TXT:
                return "Text File (*.txt)";

            case c.M3:
                return "M3 UIDs (*.m3)";

            case c.MC:
                return "MC Config (*.mc, *.conf, *.txt)";

            case c.MC_WR:
                return "MC Config (*.mc)";

            default:
                return "*.*";
        }
    }
}
