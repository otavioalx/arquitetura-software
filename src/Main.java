import domain.Product;
import service.ProductService;
import utils.GenerateValue;

void main(){
    ProductService productService = new ProductService();

    Product produto = new Product(
            GenerateValue.uuid(),
            "SKU",
            "asas",
            2f);
    produto.setPrice(3f);
    produto.setPrice(4f);
    productService.create(produto);

    productService.listAll();
}



//add o hibernate no projeto, add a dependencia, o maiven e o HYBERNATE (configurar se der)
//OU add graddle em um projeto no intelliJ (hibernate se usa OU no graddle OU maiven, nao ambos)
// add framework support
//1:26:50 do meet