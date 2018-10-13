package com.xbrain.testfelipe.restapi.services;

import com.xbrain.testfelipe.restapi.models.Ranking;
import com.xbrain.testfelipe.restapi.projection.RakingProject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class VendaService {

    public static final String createDateInitialAndFinal(Boolean dateFinal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = calcDaysOfWeek(calendar, 7 - calendar.get(Calendar.DAY_OF_WEEK));
        if (dateFinal) {
            /** Adicionando dia, no caso só retorna os dias já adicionados acima **/
            return sdf.format(date);
        }
        /** Removendo dia **/
        return sdf.format(calcDaysOfWeek(calendar, -7));
    }

    /***
     *
     * @return Date
     */
    public static Date calcDaysOfWeek(Calendar calendar, Integer days) {
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static void createListCustomRanking(RakingProject rakingProject, List<Ranking> listRaking) {
        Ranking ranking = new Ranking();
        ranking.setId(rakingProject.getId());
        ranking.setNomeVendedor(rakingProject.getNome_Vendedor());
        ranking.setRanking(rakingProject.getRanking());
        ranking.setMedia(Double.toString(Double.parseDouble(rakingProject.getRanking()) / 7));
        listRaking.add(ranking);
    }
}
