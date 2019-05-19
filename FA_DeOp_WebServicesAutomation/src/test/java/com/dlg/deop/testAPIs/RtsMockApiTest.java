package com.dlg.deop.testAPIs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.dlg.deop.artifacts.rtsMockReqPackage.Request;

public class RtsMockApiTest {

    public static void main(String[] args) {

        XmlMapper xmlMapper = new XmlMapper();
        Request rtsRequest = buildRtsRequest("FULL",123);
        try {
            String reqXml = xmlMapper.writeValueAsString(rtsRequest);
            System.out.println(reqXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Request buildRtsRequest(String rtsRqType,int rtsClaimNum) {

        Request rtsRequestType = new Request();
        Request.ClaimDocument rtsClaimDocument = new Request.ClaimDocument();
        Request.ClaimDocument.Claim rtsClaimNumber = new  Request.ClaimDocument.Claim();

        rtsClaimNumber.setClaimNumber(rtsClaimNum);
        rtsClaimDocument.setClaim(rtsClaimNumber);

        rtsRequestType.setRequestType(rtsRqType);
        rtsRequestType.setClaimDocument(rtsClaimDocument);

        return rtsRequestType;
    }
}
