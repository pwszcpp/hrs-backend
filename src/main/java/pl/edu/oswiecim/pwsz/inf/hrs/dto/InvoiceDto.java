package pl.edu.oswiecim.pwsz.inf.hrs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Contractor;

import javax.annotation.Generated;
import java.sql.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "seller_name",
        "seller_address",
        "seller_NIP",
        "seller_account_number",
        "buyer_name",
        "buyer_address",
        "buyer_NIP",
        "payment_method",
//        "issue_date",
        "sale_date",
        "payment_date",
        "goods_service",
        "quantity",
        "net_price",
        "TAX",
        "gross_price",

        //"contractor"
})
public class InvoiceDto extends ResourceSupport {


    @JsonProperty("id")
    private String id;
    @JsonProperty("seller_name")
    private String seller_name;
    @JsonProperty("seller_address")
    private String sellerAddress;
    @JsonProperty("seller_NIP")
    private long sellerNIP;
    @JsonProperty("seller_account_number")
    private long sellerAccountNumber;
    @JsonProperty("buyer_name")
    private String buyer_name;
    @JsonProperty("buyer_address")
    private String buyerAddress;
    @JsonProperty("buyer_NIP")
    private long buyerNIP;
    @JsonProperty("payment_method")
    private String paymentMethod;
//    @JsonProperty("issue_date")
//    private Date issueDte;
    @JsonProperty("sale_date")
    private Date saleDate;
    @JsonProperty("payment_date")
    private Date paymentDate;
    @JsonProperty("goods_service")
    private String goodsService;
    @JsonProperty("quantity")
    private double quantity;
    @JsonProperty("net_price")
    private double netPrice;
    @JsonProperty("TAX")
    private int tax;
    @JsonProperty("gross_price")
    private double grossPrice;
//    @JsonProperty("document_issued")
//    private String documentIssued;



//    @JsonProperty("contractor")
//    private Contractor contractor;

    @JsonProperty("id")
    public String getInvoiceId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("seller_name")
    public String getSeller_name() {
        return seller_name;
    }
    @JsonProperty("seller_name")
    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    @JsonProperty("seller_address")
    public String getSellerAddress() {
        return sellerAddress;
    }
    @JsonProperty("seller_address")
    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    @JsonProperty("seller_NIP")
    public long getSellerNIP() {
        return sellerNIP;
    }
    @JsonProperty("seller_NIP")
    public void setSellerNIP(long selllerNIP) {
        this.sellerNIP = selllerNIP;
    }

    @JsonProperty("seller_account_number")
    public long getSellerAccountNumber() {
        return sellerAccountNumber;
    }
    @JsonProperty("seller_account_number")
    public void setSellerAccountNumber(long sellerAccountNumber) {
        this.sellerAccountNumber = sellerAccountNumber;
    }

    @JsonProperty("buyer_name")
    public String getBuyer_name() {
        return buyer_name;
    }
    @JsonProperty("buyer_name")
    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    @JsonProperty("buyer_address")
    public String getBuyerAddress() {
        return buyerAddress;
    }
    @JsonProperty("buyer_address")
    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    @JsonProperty("buyer_NIP")
    public long getBuyerNIP() {
        return buyerNIP;
    }
    @JsonProperty("buyer_NIP")
    public void setBuyerNIP(long buyerNIP) {
        this.buyerNIP = buyerNIP;
    }

    @JsonProperty("payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }
    @JsonProperty("payment_method")
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

//    @JsonProperty("issue_date")
//    public Date getIssueDte() {
//        return issueDte;
//    }
//    @JsonProperty("issue_date")
//    public void setIssueDte(Date issueDte) {
//        this.issueDte = issueDte;
//    }

    @JsonProperty("sale_date")
    public Date getSaleDate() {
        return saleDate;
    }
    @JsonProperty("sale_date")
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @JsonProperty("payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }
    @JsonProperty("payment_date")
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @JsonProperty("goods_service")
    public String getGoodsService() {
        return goodsService;
    }
    @JsonProperty("goods_service")
    public void setGoodsService(String goodsService) {
        this.goodsService = goodsService;
    }

    @JsonProperty("quantity")
    public double getQuantity() {
        return quantity;
    }
    @JsonProperty("quantity")
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("net_price")
    public double getNetPrice() {
        return netPrice;
    }
    @JsonProperty("net_price")
    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    @JsonProperty("tax")
    public int getTax() {
        return tax;
    }
    @JsonProperty("tax")
    public void setTax(int tax) {
        this.tax = tax;
    }

    @JsonProperty("gross_price")
    public double getGrossPrice() {
        return grossPrice;
    }
    @JsonProperty("gross_price")
    public void setGrossPrice(double grossPrice) {
        this.grossPrice = grossPrice;
    }

//    @JsonProperty("document_issued")
//    public String getDocumentIssued() {
//        return documentIssued;
//    }
//    @JsonProperty("document_issued")
//    public void setDocumentIssued(String documentIssued) {
//        this.documentIssued = documentIssued;
//    }
//
//

//    @JsonProperty("contractor")
//    public Contractor getContractor() {
//        return contractor;
//    }
//
//    @JsonProperty("contractor")
//    public void setContractor(Contractor contractor) {
//        this.contractor = contractor;
//    }
}
