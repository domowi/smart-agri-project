package org.graphql;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.nafis.NafisRaw;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class NafisRawService {

    public List<NafisRaw> getNafisRaw() {
        PanacheQuery<NafisRaw> nafisRaw = NafisRaw.findAll();
        return nafisRaw.list();
    }

    public List<NafisRaw> getSukumaPrices(String commodity) {
        //List<NafisRaw> nafisRaw = NafisRaw.list("commodity", commodity);
        return NafisRaw.list("commodity", commodity);
    }

}
