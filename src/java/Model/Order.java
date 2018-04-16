/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;

/**
 *
 * @author Zach
 */
public class Order {
    int id, agentId, clientId, flyerQuantity, personalCopies;
    String flyerLayout, paymentInfo, invoiceNum, comments;
    Boolean flyerArtApprovl, paymentRecvd;
    Blob flyerImg;

    public Order(int agentId, int clientId, int flyerQuantity, int personalCopies, String flyerLayout, String paymentInfo, String invoiceNum, String comments, Boolean flyerArtApprovl, Boolean paymentRecvd, Blob flyerImg) {
        this.agentId = agentId;
        this.clientId = clientId;
        this.flyerQuantity = flyerQuantity;
        this.personalCopies = personalCopies;
        this.flyerLayout = flyerLayout;
        this.paymentInfo = paymentInfo;
        this.invoiceNum = invoiceNum;
        this.comments = comments;
        this.flyerArtApprovl = flyerArtApprovl;
        this.paymentRecvd = paymentRecvd;
        this.flyerImg = flyerImg;
    }
    public Order(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getFlyerQuantity() {
        return flyerQuantity;
    }

    public void setFlyerQuantity(int flyerQuantity) {
        this.flyerQuantity = flyerQuantity;
    }

    public int getPersonalCopies() {
        return personalCopies;
    }

    public void setPersonalCopies(int personalCopies) {
        this.personalCopies = personalCopies;
    }

    public String getFlyerLayout() {
        return flyerLayout;
    }

    public void setFlyerLayout(String flyerLayout) {
        this.flyerLayout = flyerLayout;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getFlyerArtApprovl() {
        return flyerArtApprovl;
    }

    public void setFlyerArtApprovl(Boolean flyerArtApprovl) {
        this.flyerArtApprovl = flyerArtApprovl;
    }

    public Boolean getPaymentRecvd() {
        return paymentRecvd;
    }

    public void setPaymentRecvd(Boolean paymentRecvd) {
        this.paymentRecvd = paymentRecvd;
    }

    public Blob getFlyerImg() {
        return flyerImg;
    }

    public void setFlyerImg(Blob flyerImg) {
        this.flyerImg = flyerImg;
    }

}
