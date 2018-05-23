package modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {
    private static final String[] MONTHS = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    /**
     * 
     * @param date, Recibe un objeto java.util.Date a convertir
     * @return date, Regresa un objeto java.lang.String con la fecha en el formato: año - mes - dia
     */
    public static String toString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    /**
     * 
     * @param date, Recibe un objeto java.lang.String con la fecha en el formato: año - mes - dia
     * @return date, Regresa un objeto java.util.Date, en caso de haber ingresado una fecha no válida, regresará la fecha actual
     */
    public static Date toDate(String date){
        Date finalDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            finalDate = simpleDateFormat.parse(date);
        } catch (ParseException exception) {
            finalDate = new Date();
        }
        return finalDate;
    }
    /**
     * 
     * @param date, Recibe un objeto java.lang.String con la fecha en el formato: año - mes - dia
     * @return dia, Regresa un primitivo int con el valor del día de la fecha
     */
    public static int getDay(Date date){
        return Integer.parseInt(Dates.toString(date).substring(8, 10));
    }
    /**
     * 
     * @param date, Recibe un objeto java.lang.String con la fecha en el formato: año - mes - dia
     * @return mes, Regresa un primitivo int con el valor del mes de la fecha: 1 = enero ... 12 = diciembre
     */
    public static int getMonth(Date date){
        return Integer.parseInt(toString(date).substring(5, 7));
    }
    /**
     * 
     * @param month, Recibe un primitivo int con valor de: 0 < month > 13
     * @return mes, Regresa un objeto java.lang.String con el nombre del mes, si el valor int no es válido, regresará una cadena vacía
     */
    public static String getMonth(int month){
        String stringMonth = "";
        for (int i = 0; i < Dates.MONTHS.length; i ++){
            if (month == i+1){
                stringMonth = Dates.MONTHS[i];
            }
        }
        return stringMonth;
    }
    /**
     * 
     * @param month, Recibe un objeto java.lang.String con el nombre del mes
     * @return mes, Regresa un primitivo int con el valor correspondiente al mes: Enero = 1 ... Diciembre = 12
     */
    public static int getMonth(String month){
        int intMonth = -1;
        for (int i = 0; i < Dates.MONTHS.length; i ++){
            if (Dates.MONTHS[i].equals(month)){
                intMonth = i + 1;
                break;
            }
        }
        return intMonth;
    }
    /**
     * 
     * @param date, Recibe un objeto java.util.Date
     * @return año, Regresa un primitivo int con el valor del año: 2008[ej]
     */
    public static int getYear(Date date){
        return Integer.parseInt(toString(date).substring(0, 4));
    }
    /**
     * 
     * @param year, Recibe un primitivo int con el año
     * @return año, Regresa un java.lang.String con el año
     */
    public static String getYear(int year){
        return String.valueOf(year);
    }
    /**
     * 
     * @param date, recibe un objeto java.util.Date
     * @return sentencia, Regresa un objeto java.lang.String con la fecha, incluyendoi el nombre del mes: 23 Abril 2008
     */
    public static String getSentence(Date date){
        String sentence;
        String month = Dates.getMonth(Dates.getMonth(date));
        int day = Dates.getDay(date);
        String year = Dates.getYear(Dates.getYear(date));
        sentence = day + " " + month + " " + year;
        return sentence;
    }
    /**
     * 
     * @param referenceDate, recibe un objeto java.util.Date con la fecha de referencia
     * @param newDate, recibe un objeto java.util.Date con la fecha que se desea comparar con la fecha de referencia
     * @return diferencia, regresa un primitivo int con la diferencia entre las fechas, en caso de ser la fecha de referencia posterior a la nueva, regresará -1
     */
    public static int getDiference(Date referenceDate, Date newDate){
        int diference;
        int referenceYear = Dates.getYear(referenceDate);
        int referenceMonth = Dates.getMonth(referenceDate);
        int referenceDay = Dates.getDay(referenceDate);
        int newYear = Dates.getYear(newDate);
        int newMonth = Dates.getMonth(newDate);
        int newDay = Dates.getDay(newDate);
        int diasMesRef = LogicCalendar.getMonthDaysNumber(referenceMonth, referenceYear);
        if (newYear >= referenceYear){
            if (newMonth == referenceMonth){
                if (newDay > referenceDay){
                    diference = newDay - referenceDay;
                }else if (newDay == referenceDay){
                    diference = 0;
                }else{
                    diference = -1;
                }
            }else if (newMonth > referenceMonth){
                diference = newDay + (diasMesRef - referenceDay);
                int auxiliarMonth = referenceMonth + 1;
                while(auxiliarMonth < newMonth){
                    diference = diference + LogicCalendar.getMonthDaysNumber(auxiliarMonth, newYear);
                    auxiliarMonth ++;
                }
            }else{
                if (newYear > referenceYear){
                    diference = newDay + (diasMesRef - referenceDay);
                    int auxiliarMonth = referenceMonth + 1;
                    while(auxiliarMonth > newMonth || auxiliarMonth < newMonth){
                        diference = diference + LogicCalendar.getMonthDaysNumber(auxiliarMonth, newYear);
                        auxiliarMonth ++;
                        if (auxiliarMonth > 12){
                            auxiliarMonth = 1;
                        }
                    }
                }else{
                    diference = -1;
                }
            }
        }else{
            diference = -1;
        }
        return diference;
    }
}
