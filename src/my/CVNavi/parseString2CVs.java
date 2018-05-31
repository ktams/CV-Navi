/*
 * parseString2CVs.java
 *
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi;

import java.util.Arrays;
import javax.swing.JTextArea;
import static my.CVNavi.CVNavi.debugLevel;

/**
 *
 * @author Lothar
 */
public class parseString2CVs {

    // given a CV number find index/offset of that number in array in CV[0]
    private static int getOffsetForCV( int nCV, int CV[][] ) {
        if( nCV <= 0 ) {
            // negative CVs and CV 0 are not supported -> return -1
            return -1;
        }
        int CVdim = CV[0].length;
        // scan all items in CV[0] for suiting value
        for( int n=0 ; n < CVdim ; n++ ) {
            if( CV[0][n] == nCV ) {
                // value matched -> report index/offset
                return n;
            }
        }
        // no match -> return -1
        return -1;
    }

    public static Boolean convertString2CV( String str, String[] keys, int CV[][], JTextArea jComment, CVNavi KTUI ) {
        int strLen = str.length();
        int keyLen = 0;
        int dimKey = keys.length;
        int dima = CV.length;
        int dimb = CV[0].length;
        int n;
        String allKeys = Arrays.toString(keys);

        if( debugLevel >= 1 ) {
            System.out.println("convertString2CV dimkey="+dimKey+" dima["+dima+"] dimb["+dimb+"] strLen["+strLen+"] a2"+allKeys);
        }
        for( n = 0 ; n < dimKey ; n++ ) {
            keyLen = keys[n].length();
            if( keyLen <= strLen ) {
                String possibleKey = str.substring(0, keyLen);
                if( debugLevel >= 1 ) {
                    System.out.println("check keylen["+keyLen+"] key["+n+"]=\""+keys[n]+"\" possibleKey=\""+possibleKey+"\"");
                }
                if( possibleKey.matches(keys[n]) ) {
                    // suitable key detected -> stop searching -> continue with work
                    System.out.println("check SUCCESS" );
                    break;
                }
            }
        }
        if( n >= dimKey ) {
            String defaultKey = "Tams Elektronik";
            keyLen = defaultKey.length();
            String possibleKey = str.substring(0, keyLen);
            System.out.println("check keylen["+keyLen+"] possibleKey=\""+possibleKey+"\"");
            if( possibleKey.matches( defaultKey ) ) {
                System.out.println("possibleKey=\""+possibleKey+"\"" );
                return false;
            }
            System.out.println("ERROR: Config file not starting with "+ allKeys );
            KTUI.mbFileReadBeginError( null, allKeys );
            return false;
        }
        else {
            System.out.println("convertString2CV matching key["+n+"]=\""+keys[n]+"\"");
        
            int strIDX;
            int iCV;
            int iEQ;
            int iEND;
            int iCOMMENT;
            String sCV;
            int nCV;
            String sVAL;
            int nVAL;
            int CVread = 0;
            int CVskipped = 0;

            strIDX = 0;
            strLen = str.length();
            iCOMMENT = str.indexOf( "Kommentar:" );
            if( iCOMMENT < 0 ) {
                iCOMMENT = strLen;
            }

            int loop = 0;
            while( strIDX < iCOMMENT ) {
                loop++;
                iCV = str.indexOf( "CV#", strIDX);
                iEQ = str.indexOf( '=', iCV );
                iEND = str.indexOf( '\n', iEQ );
                if( iCV > 0 && iEQ > 0 && iEND > 0 && iEND < strLen ) {
                    sCV = str.substring(iCV+3, iEQ);
                    sVAL = str.substring(iEQ+1, iEND-1);
                    sCV = sCV.trim();
                    sVAL = sVAL.trim();
                    try {
                        nCV = Integer.parseInt(sCV);
                    } catch (NumberFormatException numberFormatException) {
                        nCV = 0;
                    }
                    try {
                        nVAL = Integer.parseInt(sVAL);
                    } catch (NumberFormatException numberFormatException) {
                        nVAL = 0;
                    }
                    if( nCV > 0 ) {
                        if( nCV == 1 && nVAL > 255 ) {// if CV1 > 255 => force 3
                            nVAL = 3;
                        }
                        int offsetCV;
                        offsetCV = getOffsetForCV(nCV, CV);
                        if( offsetCV >= 0 ) {
                            CVread++;
                            CV[1][offsetCV] = nVAL;
                        }
                        else {
                            // unsupported CV
                            System.out.println("convertString2CV nCV["+nCV+"] at offsetCV["+offsetCV+"] nVAL["+nVAL+"] SKIPPED no suiting offset");
                        }
                    } else {
                        CVskipped++;
                    }
                    strIDX = iEND;
                } else {
                    // no more CVs -> end of loop
                    strIDX = iCOMMENT;
                }
            }
            if( iCOMMENT < strLen ) {
                // get end of comment header
                iEND = str.indexOf( '\n', iCOMMENT );
                if( iEND > 0 && iEND < strLen ) {
                    sVAL = str.substring(iEND+1, strLen);
                    jComment.setText(sVAL);
                    jComment.validate();
                }
            }
            System.out.println("convertString2CV read ["+CVread+"] CVs, skipped ["+CVskipped+"] ZEROCVs");

            return true;
        }
    }

    public static Boolean convertString2Kennlinie( String str, String[] keys, int CV[][], int fs1offset, CVNavi KTUI ) {
        int strLen = str.length();
        int keyLen = 0;
        int dimKey = keys.length;
        int dima   = CV.length;
        int dimb   = CV[0].length;
        int n;
        String allKeys = Arrays.toString(keys);

        System.out.println("convertString2Kennlinie dimkey="+dimKey+" dima["+dima+"] dimb["+dimb+"] strLen["+strLen+"] a2"+allKeys);
        for( n = 0 ; n < dimKey ; n++ ) {
            keyLen = keys[n].length();
            if( keyLen <= strLen ) {
                String possibleKey = str.substring(0, keyLen);
                System.out.println("check keylen["+keyLen+"] key["+n+"]=\""+keys[n]+"\" possibleKey=\""+possibleKey+"\"");
                if( possibleKey.matches(keys[n]) ) {
                    // suitable key detected -> stop searching -> continue with work
                    System.out.println("check SUCCESS" );
                    break;
                }
            }
        }
        if( n >= dimKey ) {
            String defaultKey = "Tams Elektronik";
            keyLen = defaultKey.length();
            String possibleKey = str.substring(0, keyLen);
            System.out.println("check keylen["+keyLen+"] possibleKey=\""+possibleKey+"\"");
            if( possibleKey.matches( defaultKey ) ) {
                System.out.println("possibleKey=\""+possibleKey+"\"");
                return false;
            }
            System.out.println("ERROR: Config file not starting with "+allKeys.toString() );
            KTUI.mbFileReadBeginError( null, allKeys );
            return false;
        }
        else {
            System.out.println("convertString2Kennlinie matching key["+n+"]=\""+keys[n]+"\"");
        
            int strIDX;
            int iFS;
            int iEQ;
            int iEND;
            String sFS;
            int nFS;
            String sVAL;
            int nVAL;
            int CVread = 0;
            int CVskipped = 0;

            strIDX = 0;
            strLen = str.length();

            while( strIDX < strLen ) {
                iFS = str.indexOf( "FS", strIDX);
                iEQ = str.indexOf( '=', iFS );
                iEND = str.indexOf( '\n', iEQ );
                if( iFS > 0 && iEQ > 0 && iEND > 0 && iEND <= strLen ) {
                    sFS = str.substring(iFS+2, iEQ);
                    sVAL = str.substring(iEQ+1, iEND-1);
                    sFS = sFS.trim();
                    sVAL = sVAL.trim();
                    System.out.println("convertString2Kennlinie sFS["+sFS+"] sVAL["+sVAL+"]" );
                    if( KTUI.checkNumRange(sFS, 1, 28 ) && KTUI.checkNumRange(sVAL, 0, 255) ) {
                        nFS = KTUI.checkAndGetStrNumRangeDef( null, sFS, 1, 28, 0, false);
                        nVAL = KTUI.checkAndGetStrNumRangeDef( null, sVAL, 0, 255, 0, false);
                        System.out.println("convertString2Kennlinie nFS["+nFS+"] nVAL["+nVAL+"]" );
                        int offsetCV;
                        offsetCV = getOffsetForCV(nFS+fs1offset-1, CV);
                        if( offsetCV >= 0 ) {
                            CVread++;
                            CV[1][offsetCV] = nVAL;
                        }
                        else {
                            // unsupported/unknown CV
                            System.out.println("convertString2Kennlinie nFS["+nFS+"] nVAL["+nVAL+"]" );
                        }
                    } else {
                        CVskipped++;
                        System.out.println("convertString2Kennlinie sFS["+sFS+"] sVAL["+sVAL+"] OUT OF RANGE" );
                    }
                    strIDX = iEND;
                } else {
                    // no more FSs -> end of loop
                    strIDX = strLen;
                }
            }
            System.out.println("convertString2Kennlinie read ["+CVread+"] CVs, skipped ["+CVskipped+"] ZEROCVs");

            return true;
        }
    }

}
