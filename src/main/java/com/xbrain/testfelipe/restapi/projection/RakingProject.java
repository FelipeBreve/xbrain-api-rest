package com.xbrain.testfelipe.restapi.projection;

/***
 * https://blog.trifork.com/2018/08/28/spring-data-native-queries-and-projections-in-kotlin/
 */
public interface RakingProject {
    Integer getId();
    String getNome_Vendedor();
    String getRanking();
    String getMedia();
}
