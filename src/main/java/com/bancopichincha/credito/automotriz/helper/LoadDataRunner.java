package cd ..

import com.bancopichincha.credito.automotriz.exception.DataDuplicateException;
import com.bancopichincha.credito.automotriz.service.BrandService;
import com.bancopichincha.credito.automotriz.service.CustomerService;
import com.bancopichincha.credito.automotriz.service.ExecutiveService;
import com.bancopichincha.credito.automotriz.service.YardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import java.io.File;

@Component
@Slf4j
public class LoadDataRunner implements CommandLineRunner {

    private BrandService brandService;
    private CustomerService customerService;

    private YardService yardService;

    private ExecutiveService executiveService;


    public static final String BRANDS ="classpath:files/marcas.csv";
    public static final String CUSTOMERS ="classpath:files/clientes.csv";
    public static final String EXECUTIVES ="classpath:files/ejecutivos.csv";
    public static final String YARDS ="classpath:files/patios.csv";

    @Autowired
    public LoadDataRunner(BrandService brandService, CustomerService customerService, YardService yardService, ExecutiveService executiveService) {
        this.brandService = brandService;
        this.customerService = customerService;
        this.yardService = yardService;
        this.executiveService = executiveService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Cargando informacion marcas ...");
        File fileBrands = new File(ResourceUtils.getFile(BRANDS).getPath());
        try {
            brandService.loadData(fileBrands);
        }catch (DataDuplicateException exception)
        {
         log.error("Error: " +exception.getMessage());
        }

        log.info("Cargando informacion clientes ...");

        File fileCustomers = new File(ResourceUtils.getFile(CUSTOMERS).getPath());
        try {
            customerService.loadData(fileCustomers);
        }catch (DataDuplicateException exception)
        {
            log.error("Error: " +exception.getMessage());
        }

        log.info("Cargando informacion patios ...");

        File fileYards = new File(ResourceUtils.getFile(YARDS).getPath());
        try {
            yardService.loadData(fileYards);
        }catch (DataDuplicateException exception)
        {
            log.error("Error: " +exception.getMessage());
        }

        log.info("Cargando informacion ejecutivos ...");
        File fileExecutives = new File(ResourceUtils.getFile(EXECUTIVES).getPath());
       try {
            executiveService.loadData(fileExecutives);
        }catch (DataDuplicateException exception)
        {
            log.error("Error: " +exception.getMessage());
        }


    }


}
