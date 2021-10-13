package com.ardctraining.storefront.controllers.pages;

import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.feedback.CustomerFeedbackFacade;
import com.ardctraining.storefront.controllers.ControllerConstants;
import com.ardctraining.storefront.form.CustomerFeedbackForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.dom4j.util.StringUtils;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/feedback")
public class CustomerFeedbackController extends AbstractPageController {

    @Resource(name = "customerFeedbackFacade")
    private CustomerFeedbackFacade customerFeedbackFacade;

    @Resource(name = "customerFeedbackService")
    private CustomerFeedbackService customerFeedbackService;


    @GetMapping
    public String productDetail(final Model model) throws CMSItemNotFoundException {
        preparePageAttributes(model);
        return "/pages/feedback/feedbackPage";
    }

    private void preparePageAttributes(Model model) throws CMSItemNotFoundException{
        CustomerFeedbackForm messageFeedbackForm = new CustomerFeedbackForm();
        model.addAttribute("feedbackForm",messageFeedbackForm);
        model.addAttribute("feedbacks",customerFeedbackFacade.findByStatus());
        final ContentPageModel contentPage = getContentPageForLabelOrId("feedback");
        storeCmsPageInModel(model, contentPage);
        setUpMetaDataForContentPage(model, contentPage);
    }

    @PostMapping("/save")
    public String save(CustomerFeedbackForm customerFeedbackForm, Model model) throws CMSItemNotFoundException {
        customerFeedbackFacade.save(customerFeedbackForm.getSubject(), customerFeedbackForm.getMessage());
        preparePageAttributes(model);
        return "/pages/feedback/feedbackPage";
    }

}
