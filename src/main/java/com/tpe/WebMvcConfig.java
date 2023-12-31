package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
//6  @Configuration , @ComponentScan ,@EnableWebMvc yap
@Configuration//bu classta configurasyon olacak
@ComponentScan("com.tpe")
@EnableWebMvc//MVC config etkinleştirmek
public class WebMvcConfig implements WebMvcConfigurer {

    //7  view resolver(goruntu cozumleyici, modelandview donduruyor) icin bazi ayarlar. view resolve icin(goruntu dosyalarini cozumemek icin) bean olusturalim
    @Bean
    public InternalResourceViewResolver resolver(){//InternalResourceViewResolver:bir tane resolver objesi olusturcak
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();//bir instance oluştur
        resolver.setViewClass(JstlView.class);//java etiketlerini kullanmak icin, JavaStandartTagLibrary:JSP içine daha az kod yazmamızı sağlar
        resolver.setPrefix("/WEB-INF/views/");//controllerden view dosyasinin ismi geldiginde dosyadan onu bulup yuklemek icin view dosyalarınin yeri. .jsp dosyalari  nerede
        resolver.setSuffix(".jsp");//view namein uzantısını belirliyoruz
        return resolver;
    }


    //8 dinamik web uygulamalari da statik sayfalar icerir. statik sayfa requesti gelirse. dogrudan web surver dondurebilir statik sayfalari
    //statik kaynakların dispatchera gönderilmesine gerek yok
    //bu nedenle implement WebMvcConfigurer diyoruz. burdan addResourceHandlers methodunu override ettik
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//bizim statik satfalar webapp icindeki resources icindeki css dosyalari
        registry.addResourceHandler("/resources/**").//bu pathdeki kaynakları statik olarak sun yani servlete gonderme
                addResourceLocations("/resources/").//kaynakların yeri
                setCachePeriod(0);//cacheleme için süre verilebilir.0 dedigimizde her seferinde guncel olarak gelsin diyoruz cashde beklemesin
    }
}
//9 icin rootContexConfige git
