package com.dlg.deop.testAPIs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.dlg.deop.artifacts.*;
import rtsMockReqPackage.Request;

public class rtsMockApiTest {

    public static void main(String[] args) {

        XmlMapper xmlMapper = new XmlMapper();
        RTSRequest rtsRequest = buildrtsRequest("FULL",123);
        String reqxml = xmlMapper.writeValueAsString(new rtsRequest());
        system.out.println(reqxml);
    }

    public class RTSRequest buildrtsRequest(String rtsRqType,int rtsClaimNum) {

        rtsMockReqPackage.Request rtsRequestType = new rtsMockReqPackage.Request();
        Request.ClaimDocument rtsClaimDocument = new Request.ClaimDocument();
        Request.ClaimDocument.Claim rtsClaimNumber = new  Request.ClaimDocument.Claim();
          
        
        rtsRequestType.setRequestType(rtsRqType);
        //rtsRequestType.setClaimDocument(document);
        rtsClaimNumber.setClaimNumber(rtsClaimNum);
        //rtsClaimDocument.setClaim(claim);

return rtsRequest;



    }
}
