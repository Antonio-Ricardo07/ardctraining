package com.ardctraining.core.feedback.dao.impl;

import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.core.feedback.dao.CustomerFeedbackDao;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DefaultCustomerFeedbackDao implements CustomerFeedbackDao{

    private FlexibleSearchService flexibleSearchService;

    private static final String FIND_FEEDBACK_BY_CUSTOMER_STATUS_NOT_READ = "SELECT {" + ItemModel.PK + "} " +
            "FROM   {" + CustomerFeedbackModel._TYPECODE + "} " +
            "WHERE  {" + CustomerFeedbackModel.CUSTOMER + "} =?customer AND " +
            "       {" + CustomerFeedbackModel.READ + "} =FALSE";


    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<CustomerFeedbackModel> findByStatus(CustomerModel customer) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_FEEDBACK_BY_CUSTOMER_STATUS_NOT_READ);
        query.addQueryParameter("customer",customer);
        return feedbackResult(query);
    }

    private List<CustomerFeedbackModel> feedbackResult (FlexibleSearchQuery flexibleSearchQuery){
        final SearchResult<CustomerFeedbackModel> searchResult = getFlexibleSearchService().search(flexibleSearchQuery);
        if(Objects.nonNull(searchResult) && CollectionUtils.isNotEmpty(searchResult.getResult())){
            return searchResult.getResult();
        }
        return Collections.emptyList();
    }
}
