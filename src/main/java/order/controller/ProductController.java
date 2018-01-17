package order.controller;

import order.dto.ProductDTO;
import order.entity.Product;
import order.services.ProductServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


public class ProductController {
    @Autowired
    ProductServices productServices;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Product product){
        productServices.add(product);
        if (product != null)
            return new ResponseEntity<>("Inserted " + product.getpName(), HttpStatus.OK);
        else
            return new ResponseEntity<>("There was an error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public ResponseEntity<List<ProductDTO>> getByIdList(@RequestBody ArrayList<String> productIds){
//        System.out.println(productIds);
        List <Product> productList = productServices.findByProductIds(productIds);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList.forEach(product -> {
            ProductDTO newObj = new ProductDTO();
            BeanUtils.copyProperties(product, newObj);
            productDTOList.add(newObj);
        });
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @RequestMapping("/getOne/{productId}")
    public ResponseEntity<Product> getOne(@PathVariable("productId") String productId){
        return new ResponseEntity<>(productServices.findByProductId(productId), HttpStatus.OK);
    }
    @RequestMapping("/getAll")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productServices.findAll(), HttpStatus.OK);
    }

}
