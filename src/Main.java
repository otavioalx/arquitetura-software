import domain.Product;

void main(){
    Product product = new Product();

        product.setName("Celular");
        product.setSku("TSH-BLU-MED");
        product.setPrice(new BigDecimal("1000"));
        product.setPrice(new BigDecimal("999"));

//        IO.println(product.toString());
        System.out.println(product.toString());
}
