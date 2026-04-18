import domain.Product;
import service.ProductService;
import utils.GenerateValue;

void main(){
    ProductService productService = new ProductService();

    productService.create(new Product(
                GenerateValue.uuid(),
                "SKU",
                "asas",
                2f)
    );


    productService.listAll();
}

