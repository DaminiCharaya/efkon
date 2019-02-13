package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;
import org.apache.catalina.webresources.ClasspathURLStreamHandler;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;

@Repository
public class TagsDaoImpl implements TagsDao {


    @PersistenceContext
    private EntityManager entityManager;

    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatus(Integer customerType) {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    public List<TagResponse> fetchTagCountByCustomerTypeAndByMonth(Integer customerType, Integer month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(String.valueOf(month));
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer monthofdate = Cal.get(Calendar.MONTH) + 1;
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and MONTH(sm_card.SM_DT_ISSUE)='" + monthofdate + "'");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    public List<TagResponse> fetchTagCountGroupByCustomerType() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<?> fetchTagCountByDateGroupByCustomerType(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where  sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and MONTH(sm_card.SM_DT_ISSUE)='" + month + "' and  YEAR(sm_card.SM_DT_ISSUE)='" + year + "' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();


    }

    @Override
    public List<?> fetchTagCountBySameDateGroupByCustomerType(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Integer day = Cal.get(Calendar.DAY_OF_MONTH);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where  sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and MONTH(sm_card.SM_DT_ISSUE)='" + month + "' and  YEAR(sm_card.SM_DT_ISSUE)='" + year + "' and DAY(sm_card.SM_DT_ISSUE)='" + day + "' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<?> fetchTagCountByDateAndByCustomerType(Integer customerType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and MONTH(sm_card.SM_DT_ISSUE)='" + month + "' and YEAR(sm_card.SM_DT_ISSUE)='" + year + "' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE] ");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();

    }

    @Override
    public List<TagResponse> noOfTagsIssuedInGivenDates(String firstdate, String seconddate, String thirddate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedFirstDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedSecondDate);
        Integer monthofseconddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofseconddate = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedThirdDate);
        Integer monthofthirddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofthirddate = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and (MONTH(sm_card.SM_DT_ISSUE)='" + month + "'and YEAR(sm_card.SM_DT_ISSUE)='" + year + "' or MONTH(sm_card.SM_DT_ISSUE)='" + monthofseconddate + "'and YEAR(sm_card.SM_DT_ISSUE)='" + yearofseconddate + "' or MONTH(sm_card.SM_DT_ISSUE)='" + monthofthirddate + "'and YEAR(sm_card.SM_DT_ISSUE)='" + yearofthirddate + "' )group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<TagResponse> noOfTagsIssuedInGivenDatesByCustomerType(String firstdate, String seconddate, String thirddate, Integer customerType) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedFirstDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedSecondDate);
        Integer monthofseconddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofseconddate = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedThirdDate);
        Integer monthofthirddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofthirddate = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and (MONTH(sm_card.SM_DT_ISSUE)='" + month + "'and YEAR(sm_card.SM_DT_ISSUE)='" + year + "' or MONTH(sm_card.SM_DT_ISSUE)='" + monthofseconddate + "'and YEAR(sm_card.SM_DT_ISSUE)='" + yearofseconddate + "' or MONTH(sm_card.SM_DT_ISSUE)='" + monthofthirddate + "'and YEAR(sm_card.SM_DT_ISSUE)='" + yearofthirddate + "')group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<TagResponse> fetchTagCountGroupByCustomerTypeAndByMonth(Integer month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(String.valueOf(month));
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer monthofdate = Cal.get(Calendar.MONTH) + 1;
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID  where MONTH(sm_card.SM_DT_ISSUE)='" + monthofdate + "' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<TagResponse> fetchTagCountGroupByCustomerTypeAndSortByMonth() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,MONTH(sm_card.[SM_DT_ISSUE]) month,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID  where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by MONTH(sm_card.[SM_DT_ISSUE]),sm_cust.[CUST_TYPE] order by MONTH(sm_card.[SM_DT_ISSUE])");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatusAndSortByMonth(Integer customerType) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status ,MONTH(sm_card.[SM_DT_ISSUE]) month ,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],MONTH(sm_card.SM_DT_ISSUE) order by MONTH(sm_card.SM_DT_ISSUE)");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<TagResponse> fetchTagCountByCustomerTypeAndSortByMonth(Integer customerType) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,MONTH(sm_card.[SM_DT_ISSUE]) month FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID  where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by MONTH(sm_card.[SM_DT_ISSUE]) order by MONTH(sm_card.[SM_DT_ISSUE])");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<?> noOfTagsOfRetailerByStatus(Integer status) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT COUNT(DISTINCT CA.[TAG_ID]) FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] WHERE CA.[SM_CARD_STATUS]=:status and CU.[CUST_TYPE]=1");
        query.setParameter("status", status);
        return query.list();
    }

    @Override
    public List<?> newTagActivationRateForRetail(Integer noOfTags,Integer status,Integer year) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(CA.[TAG_ID])/'"+noOfTags+"')*100 FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] INNER JOIN [dbo].[TXN_FLEET_MEDIA_USAGE] TXN ON TXN.[SM_CUST_ID]=CA.[SM_CUST_ID] AND TXN.[WALLET_ID]=CA.[WALLET_ID] WHERE CA.[SM_CARD_STATUS]=:status AND CU.[CUST_TYPE]=1 AND MONTH(CA.[SM_DT_ISSUE]) IN ('10','11','12') AND YEAR(CA.[SM_DT_ISSUE])='"+year+"' AND MONTH(TXN.[TXN_DATE])IN ('10','11','12') AND YEAR(TXN.[TXN_DATE])='"+year+"'");
        query.setParameter("status",status);
        return query.list();
    }

    public List<?> newTagActivationRateForCorporate(Integer noOfTags,Integer status,Integer year) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(CA.[TAG_ID])/'"+noOfTags+"')*100 FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] INNER JOIN [dbo].[TXN_FLEET_MEDIA_USAGE] TXN ON TXN.[SM_CUST_ID]=CU.[SM_CUST_ID] AND TXN.[WALLET_ID]=CU.[WALLET_ID] WHERE CA.[SM_CARD_STATUS]=:status AND CU.[CUST_TYPE]=2 AND MONTH(CA.[SM_DT_ISSUE]) IN ('10','11','12') AND YEAR(CA.[SM_DT_ISSUE])='"+year+"' AND MONTH(TXN.[TXN_DATE])IN ('10','11','12') AND YEAR(TXN.[TXN_DATE])='"+year+"'");
        query.setParameter("status",status);
        return query.list();
    }

    @Override
    public List<?> noOfTagsOfCorporateByStatus(Integer status) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT COUNT(DISTINCT CA.[TAG_ID]) FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] WHERE CA.[SM_CARD_STATUS]=:status and CU.[CUST_TYPE]=2");
        query.setParameter("status", status);
        return query.list();
    }

    @Override
    public List<?> frequencyOfUsageForRetail(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT CA.[TAG_ID],COUNT(TXN.[TOLL_TXNTIMESTAMP]) Transaction_Count FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] INNER JOIN [dbo].[TXN_FLEET_MEDIA_USAGE] TXN ON TXN.[SM_CUST_ID]=CA.[SM_CUST_ID] AND TXN.[WALLET_ID]=CA.[WALLET_ID] WHERE ISNULL(CA.[TAG_ID],'')<>'' AND CA.[SM_CARD_STATUS]=0 and CU.[CUST_TYPE]=1 AND MONTH(CA.[SM_DT_ISSUE]) IN ('"+month+"') AND YEAR(CA.[SM_DT_ISSUE])='"+year+"' GROUP BY CA.[TAG_ID] ORDER BY CA.[TAG_ID]");
        return query.list();
    }

    public List<?> frequencyOfUsageForCorporate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT CA.[TAG_ID],COUNT(TXN.[TOLL_TXNTIMESTAMP]) Transaction_Count FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] INNER JOIN [dbo].[TXN_FLEET_MEDIA_USAGE] TXN ON TXN.[SM_CUST_ID]=CA.[SM_CUST_ID] AND TXN.[WALLET_ID]=CA.[WALLET_ID] WHERE ISNULL(CA.[TAG_ID],'')<>'' AND CA.[SM_CARD_STATUS]=0 and CU.[CUST_TYPE]=2 AND MONTH(CA.[SM_DT_ISSUE]) IN ('"+month+"') AND YEAR(CA.[SM_DT_ISSUE])='"+year+"' GROUP BY CA.[TAG_ID] ORDER BY CA.[TAG_ID]");
        return query.list();
    }

    @Override
    public List<?> noOfTagsByStatus(Integer status) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT COUNT(DISTINCT CA.[TAG_ID]),CU.[CUST_TYPE] custType FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] WHERE CA.[SM_CARD_STATUS]=:status group by CU.[CUST_TYPE]");
        query.setParameter("status", status);
        return query.list();
    }


    public List<?> noOfNewTagsIssuedInOctNovDecByStatusForRetailer(Integer status,Integer year) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT COUNT(CA.[TAG_ID]) FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] WHERE CA.[SM_CARD_STATUS]=:status and CU.[CUST_TYPE]=1 AND MONTH(CA.[SM_DT_ISSUE]) IN ('10','11','12') AND YEAR(CA.[SM_DT_ISSUE])='"+year+"'");
        query.setParameter("status", status);
        return query.list();
    }


    public List<?> noOfNewTagsIssuedInOctNovDecByStatusForCorporate(Integer status,Integer year) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT COUNT(CA.[TAG_ID]) FROM [dbo].[MD_SM_CARD] CA INNER JOIN [dbo].[MD_SM_CUST] CU ON CU.[SM_CUST_ID]=CA.[SM_CUST_ID] WHERE CA.[SM_CARD_STATUS]=:status and CU.[CUST_TYPE]=2 AND MONTH(CA.[SM_DT_ISSUE]) IN ('10','11','12') AND YEAR(CA.[SM_DT_ISSUE])='"+year+"'");
        query.setParameter("status", status);
        return query.list();
    }
    public List<?> active30forretail(Integer noOfTags, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(DISTINCT CA.TAG_ID)/'" + noOfTags + "')*100 FROM MD_SM_CARD CA INNER JOIN MD_SM_CUST CU ON CU.SM_CUST_ID=CA.SM_CUST_ID INNER JOIN TXN_FLEET_MEDIA_USAGE TXN ON TXN.SM_CUST_ID=CU.SM_CUST_ID AND TXN.WALLET_ID=CU.WALLET_ID WHERE CA.SM_CARD_STATUS=0 and CU.CUST_TYPE=1 AND MONTH(TXN.TXN_DATE)='" + month + "' AND YEAR(TXN.TXN_DATE)='" + year + "'");
        return query.list();
    }


    public List<?> activationrateforretail(Integer noOfTags, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(DISTINCT CA.TAG_ID)/'" + noOfTags + "')*100 FROM MD_SM_CARD CA INNER JOIN MD_SM_CUST CU ON CU.SM_CUST_ID=CA.SM_CUST_ID INNER JOIN TXN_FLEET_MEDIA_USAGE TXN ON TXN.SM_CUST_ID=CA.SM_CUST_ID AND TXN.WALLET_ID=CA.WALLET_ID WHERE CA.SM_CARD_STATUS=0 and CU.CUST_TYPE=1 AND MONTH(TXN.TXN_DATE)='" + month + "' AND YEAR(TXN.TXN_DATE)='" + year + "'");
        return query.list();
    }


    public List<?> activationrateforcorporate(Integer noOfTags, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(DISTINCT CA.TAG_ID)/'" + noOfTags + "')*100 FROM MD_SM_CARD CA INNER JOIN MD_SM_CUST CU ON CU.SM_CUST_ID=CA.SM_CUST_ID INNER JOIN TXN_FLEET_MEDIA_USAGE TXN ON TXN.SM_CUST_ID=CA.SM_CUST_ID AND TXN.WALLET_ID=CA.WALLET_ID WHERE CA.SM_CARD_STATUS=0 and CU.CUST_TYPE=2 AND MONTH(TXN.TXN_DATE)='" + month + "' AND YEAR(TXN.TXN_DATE)='" + year + "'");
        return query.list();
    }

    public List<?> activationrate(Integer noOfTags, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(DISTINCT CA.TAG_ID)/'" + noOfTags + "')*100 count,CU.[CUST_TYPE] customerType FROM MD_SM_CARD CA INNER JOIN MD_SM_CUST CU ON CU.SM_CUST_ID=CA.SM_CUST_ID INNER JOIN TXN_FLEET_MEDIA_USAGE TXN ON TXN.SM_CUST_ID=CA.SM_CUST_ID AND TXN.WALLET_ID=CA.WALLET_ID WHERE CA.SM_CARD_STATUS=0 AND MONTH(TXN.TXN_DATE)='" + month + "' AND YEAR(TXN.TXN_DATE)='" + year + "' group by CU.[CUST_TYPE]");
        return query.list();
    }


    public List<TagResponse> active30(Integer noOfTags, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(DISTINCT CA.TAG_ID)/'" + noOfTags + "')*100 count,CU.CUST_TYPE customerType FROM MD_SM_CARD CA INNER JOIN MD_SM_CUST CU ON CU.SM_CUST_ID=CA.SM_CUST_ID INNER JOIN TXN_FLEET_MEDIA_USAGE TXN ON TXN.SM_CUST_ID=CU.SM_CUST_ID AND TXN.WALLET_ID=CU.WALLET_ID WHERE CA.SM_CARD_STATUS=0  AND MONTH(TXN.TXN_DATE)='" + month + "' AND YEAR(TXN.TXN_DATE)='" + year + "' group by CU.CUST_TYPE");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class)).list();
    }

    public List<?> active30forcorporate(Integer noOfTags, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT (COUNT(DISTINCT CA.TAG_ID)/'" + noOfTags + "')*100 FROM MD_SM_CARD CA INNER JOIN MD_SM_CUST CU ON CU.SM_CUST_ID=CA.SM_CUST_ID INNER JOIN TXN_FLEET_MEDIA_USAGE TXN ON TXN.SM_CUST_ID=CU.SM_CUST_ID AND TXN.WALLET_ID=CU.WALLET_ID WHERE CA.SM_CARD_STATUS=0 and CU.CUST_TYPE=2 AND MONTH(TXN.TXN_DATE)='" + month + "' AND YEAR(TXN.TXN_DATE)='" + year + "'");
        return query.list();
    }

}
