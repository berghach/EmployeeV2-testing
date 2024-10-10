package servlet;

import service.CandidateService;
import service.JobOfferService;

import javax.servlet.http.HttpServlet;

public class ManageRecruitingServlet extends HttpServlet {
    private JobOfferService jobOfferService;
    private CandidateService candidateService;
}
