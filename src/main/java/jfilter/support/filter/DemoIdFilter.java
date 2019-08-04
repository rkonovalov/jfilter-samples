package jfilter.support.filter;

import com.comparator.Comparator;
import com.jfilter.filter.DynamicFilterComponent;
import com.jfilter.filter.DynamicFilterEvent;
import com.jfilter.filter.FilterFields;
import com.jfilter.request.RequestSession;

import java.util.Arrays;

@DynamicFilterComponent
public class DemoIdFilter implements DynamicFilterEvent {
    @Override
    public void onRequest(Comparator<RequestSession, FilterFields> comparator) {
        //If request has SOME_KEY in request params then try to filter fields "id", "password", "email" from object
        comparator.compare((request -> request.getRequest().getParameterMap().containsKey("SOME_KEY")),
                (result -> FilterFields.getFieldsBy(Arrays.asList("id", "password", "email", "address"))));

    }
}