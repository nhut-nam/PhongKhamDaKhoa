//<%
//    //Begin process return from VNPAY
//    Map fields = new HashMap();
//    for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
//    String fieldName = (String) params.nextElement();
//    String fieldValue = request.getParameter(fieldName);
//    if ((fieldValue != null) && (fieldValue.length() > 0)) {
//        fields.put(fieldName, fieldValue);
//    }
//    }
//    
//    String vnp_SecureHash = request.getParameter("vnp_SecureHash");
//    if (fields.containsKey("vnp_SecureHashType")) {
//    fields.remove("vnp_SecureHashType");
//    }
//    if (fields.containsKey("vnp_SecureHash")) {
//    fields.remove("vnp_SecureHash");
//    }
//    String signValue = Config.hashAllFields(fields);
//    
//    %>
//    <%
//    if (signValue.equals(vnp_SecureHash)) {
//        if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
//            out.print("GD Thanh cong");
//        } else {
//            out.print("GD Khong thanh cong");
//        }
//    
//    } else {
//        out.print("Chu ky khong hop le");
//    }
//    %>