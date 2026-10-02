/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package printingservicemanagement;

import java.time.LocalDate;

public class DateSimulationHandler {
    private static LocalDate currentDate = LocalDate.now();

    public static LocalDate getDate() {
        return currentDate;
    }

    public static void setDate(LocalDate date) {
        currentDate = date;
    }
}
