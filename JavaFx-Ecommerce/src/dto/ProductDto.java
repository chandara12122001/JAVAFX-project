package dto;

public class ProductDto {
    private Integer _productId;
    private Integer _productCategoryId;
    private String _productTitle;
    private Double _productPrice;
    private String _productImage;
    private String _productDescription;

    //getter&setter
    public Integer get_productId() {
        return _productId;
    }
    public void set_productId(Integer _productId) {
        this._productId = _productId;
    }
    public Integer get_productCategoryId() {
        return _productCategoryId;
    }
    public void set_productCategoryId(Integer _productCategory) {
        this._productCategoryId = _productCategory;
    }
    public String get_productTitle() {
        return _productTitle;
    }
    public void set_productTitle(String _productTitle) {
        this._productTitle = _productTitle;
    }
    public Double get_productPrice() {
        return _productPrice;
    }
    public void set_productPrice(Double _productPrice) {
        this._productPrice = _productPrice;
    }
    public String get_productImage() {
        return _productImage;
    }
    public void set_productImage(String _productImage) {
        this._productImage = _productImage;
    }
    public String getProductDescription() {
        return _productDescription;
    }
    public void setProductDescription(String productDescription) {
        this._productDescription = productDescription;
    }

    //constructor
    public ProductDto(Integer _productId, Integer _productCategoryId, String _productTitle, Double _productPrice,
            String _productImage, String productDescription) {
        this._productId = _productId;
        this._productCategoryId = _productCategoryId;
        this._productTitle = _productTitle;
        this._productPrice = _productPrice;
        this._productImage = _productImage;
        this._productDescription = productDescription;
    }
    public ProductDto() {}

    @Override
    public String toString() {
        String output = "";
        output.concat(get_productId()+" ");
        output.concat(get_productCategoryId()+" ");
        output.concat(get_productTitle()+" ");
        output.concat(get_productPrice()+" ");
        output.concat(get_productImage()+" ");
        output.concat(getProductDescription());
        return output;
    }
    
}
