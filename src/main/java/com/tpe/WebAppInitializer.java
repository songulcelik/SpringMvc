package com.tpe;
/*
s.mvc springin projelerinden biri olan s.framework ün java ile web uyg gelismek icin olusturulan modulu
model:datalarin modellendigi yer
view:kullaniciya son goruntunun sonucun sunuldugu katman
controller: requestlerin karsilanip data bulmak gerekiyorsa  bulup manipule edilip bu modelin hangi goruntu dosyasiyla istemciye sunulacaksa bulur .dosyasiinn ismini view katmanina gonderilir
dinamil web app:kullaniciyla etkilesime gecen kullaniciya gore degisiklik gosteren
restful service(rest api) uretmek icin kullanilir
dispatcher servlet: requestleri tek merkezde toplayip ilgili controllere yonlendirmesini sagliyor
3-layer architechure: datalari olabildigince kullanicidan uzak tutmak
controller-->service-->repository
controller<--service<--repository
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//1 bu classi olustur.
//Java tabanlı web uyg. web.xml dosyası ile config edilir.
//web.xml yerine bu claası kullanacağız. web.xml i sildik. neden cunku mvc kod bazli config imkani sunuyor

////2 extend ettik override ettik
//AbstractAnnot... : classının metodlarını override ederek DispatcherServletın config
// ve başlatılmasını kolaylaştırır.


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /*
   dispatcherin bilesenleri : Servlet WebbAppContext-->controller-viewresolver-handlermapping,, goruntu dosyasinin cozumlenmesini saglar-
                               Root WebApp Context-->dataya erisimle ilgili bilesenleri iceriyor:service-repositor
   bu bilenleri config etmemiz lazim
    */

    //5
    @Override////dataya erisim(hibernate-jdbc) icin RootContextConfig.class a git
    protected Class<?>[] getRootConfigClasses() {//dataya erişim(hibernate-jdbc)
        return new Class[]{
                RootContextConfig.class
        };
    }


    //4 mcv configurasyon ayarlari icin. WebMvcConfig classi olusturduk
    @Override
    protected Class<?>[] getServletConfigClasses() {//controllers-viewresolver-handlermapping(SpringMVC config)
        return new Class[]{
                WebMvcConfig.class//WebMcvConfig.class a git diyorum
        };
    }

    //3
    @Override//hangi url ile gelen istekler servlet tarafından karşılanacak
    protected String[] getServletMappings() {
        return new String[]{// "/" ile gelen tum istekleri karsila
                "/"
        };
    }


}
