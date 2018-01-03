package pl.edu.oswiecim.pwsz.inf.hrs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Invoice",  schema = "HRS_SCH")
public class Invoice {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "HRS_SCH.INVOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;
    @Column(name = "Seller_Name")
    private String seller_name;
    @Column(name = "Seller_Address")
    private String sellerAddress;
    @Column(name = "Seller_NIP")
    private long sellerNIP;
    @Column(name = "Seller_Account_Number")
    private long sellerAccountNumber;
    @Column(name = "Buyer_Name")
    private String buyer_name;
    @Column(name = "Buyer_Address")
    private String buyerAddress;
    @Column(name = "Buyer_NIP")
    private long buyerNIP;
    @Column(name = "Payment_Method")
    private String paymentMethod;
//    @Column(name = "Issue_Date")
//    private Date issueDate;
    @Column(name = "Sale_Date")
    private Date saleDate;
    @Column(name = "Payment_Date")
    private Date paymentDate;
    @Column(name = "Goods_Service")
    private double goodsService;
    @Column(name = "Quantity")
    private double quantity;
    @Column(name = "Net_Price ")
    private double netPrice;
    @Column(name = "TAX")
    private int tax;
    @Column(name = "Gross_Price")
    private double grossPrice;
//    @Column(name = "Document_Issued")
//    private String documentIssued;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public long getSellerNIP() {
        return sellerNIP;
    }

    public void setSellerNIP(long sellerNIP) {
        this.sellerNIP = sellerNIP;
    }

    public long getSellerAccountNumber() {
        return sellerAccountNumber;
    }

    public void setSellerAccountNumber(long sellerAccountNumber) {
        this.sellerAccountNumber = sellerAccountNumber;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public long getBuyerNIP() {
        return buyerNIP;
    }

    public void setBuyerNIP(long buyerNIP) {
        this.buyerNIP = buyerNIP;
    }

    public String getPaymentMethod() { return paymentMethod; }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

//    public Date getIssueDate() {
//        return issueDate;
//    }
//
//    public void setIssueDate(Date issueDte) {
//        this.issueDate = issueDate;
//    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getGoodsService() {
        return goodsService;
    }

    public void setGoodsService(double goodsService) {
        this.goodsService = goodsService;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(double grossPrice) {
        this.grossPrice = grossPrice;
    }

//    public String getDocumentIssued() {
//        return documentIssued;
//    }
//
//    public void setDocumentIssued(String documentIssued) {
//        this.documentIssued = documentIssued;
//    }
//



    //    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "contractor_id", nullable = false)
//    private Contractor contractor;

//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "contractor_id", nullable = false)
//    public Contractor getContractor() {
//        return contractor;
//    }
//
//    public void setContractor(Contractor contractor) {
//        this.contractor = contractor;
//  }


}
