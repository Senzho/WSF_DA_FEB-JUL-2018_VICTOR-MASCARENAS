package BL;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperacionesString {
    public static boolean coincide(String clave, String cadena) {
        clave = OperacionesString.sinAcentosYMayusculas(clave);
        cadena = OperacionesString.sinAcentosYMayusculas(cadena);
        boolean coincide = false;
        int largoCadena = cadena.length();
        int largoClave = clave.length();
        for (int i = 0; i < largoCadena - largoClave + 1; i++) {
            if (cadena.substring(i, i + largoClave).equals(clave)) {
                coincide = true;
                break;
            }
        }
        return coincide;
    }
    public static String sinAcentosYMayusculas(String texto) {
        String clean = "";
        if (!texto.equals("")) {
            String value = texto.toUpperCase();
            clean = Normalizer.normalize(value, Normalizer.Form.NFD);
            clean = clean.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            clean = Normalizer.normalize(clean, Normalizer.Form.NFC);
        }
        return clean;
    }
    public static boolean emailValido(String email) {
        boolean valido = false;
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher coincidencia = patron.matcher(email);
        if (coincidencia.find() && email.length() <= 150) {
            valido = true;
        }
        return valido;
    }
    public static boolean telefonoValido(String telefono) {
        boolean valido = false;
        Pattern patron = Pattern.compile("^[0-9]*$");
        Matcher coincidencia = patron.matcher(telefono);
        if (coincidencia.find() && telefono.length() == 10) {
            valido = true;
        }
        return valido;
    }
    public static boolean montoValido(String monto) {
        boolean valido = false;
        if (monto.length() > 2 && monto.length() < 6) {
            try {
                Double.valueOf(monto);
                valido = true;
            } catch (NumberFormatException excepcion) {
                valido = false;
            }
        }
        return valido;
    }
    public static boolean porcentajeValido(String monto) {
        boolean valido = false;
            try {
                Double.valueOf(monto);
                valido = true;
            } catch (NumberFormatException excepcion) {
                valido = false;
            }
        
        return valido;
    }
    public static String obtenerNombreUsuario(String correo){
        String nombreUsuario;
        if (correo.length() > 20){
            nombreUsuario = correo.substring(0, 19);
        }else{
            nombreUsuario = correo;
        }
        return nombreUsuario;
    }
    public static boolean URLValida(String URL) {
        String patron = "^(https?://)?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";
        boolean urlValida = false;
        Pattern patt = Pattern.compile(patron);
        Matcher matcher = patt.matcher(URL);
        urlValida =  matcher.matches();
        return urlValida;
    }
}
