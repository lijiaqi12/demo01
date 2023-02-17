package priv.jesse.mall.web.user;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.log.ShoppingLogs;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.*;
import priv.jesse.mall.utils.TimeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@CrossOrigin
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    HDFSService hdfsService;
    @Autowired
    HBaseService hBaseService;
    /**
     * 获取商品信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/get.do")
    public ResultBean<Product> getProduct(int id) {
        Product product = productService.findById(id);
        return new ResultBean<>(product);
    }

    /**
     * 打开商品详情页面
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/get.html")
    public String toProductPage(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        map.put("product", product);
        return "mall/product/info";
    }

    /**
     * 查找热门商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/hot.do")
    public ResultBean<List<Product>> getHotProduct() {
        List<Product> products = productService.findHotProduct();
        return new ResultBean<>(products);
    }

    /**
     * 查找最新商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/new.do")
    public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findNewProduct(pageable);
        return new ResultBean<>(products);
    }

    /**
     * 打开分类查看商品页面
     *
     * @return
     */
    @RequestMapping("/category.html")
    public String toCatePage(int cid, Map<String, Object> map) {
        Classification classification = classificationService.findById(cid);
        map.put("category", classification);
        return "mall/product/category";
    }

    @RequestMapping("/toCart.html")
    public String toCart(){
        return "mall/product/cart";
    }

    /**
     * 按一级分类查找商品
     *
     * @param cid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/category.do")
    public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCid(cid, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 按二级分类查找商品
     *
     * @param csId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/categorySec.do")
    public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCsid(csId, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 根据一级分类查询它所有的二级分类
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCategorySec.do")
    public ResultBean<List<Classification>> getCategorySec(int cid){
        List<Classification> list = classificationService.findByParentId(cid);
        return new ResultBean<>(list);
    }

    /**
     * 加购物车
     *
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCart.do")
    public ResultBean<Boolean> addToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.addCart(productId, request);
        return new ResultBean<>(true);
    }

//    @ResponseBody
//    @RequestMapping("/addHDFS")
//    public void addToHDFS(int productId,HttpSession session) throws Exception {
//        //调用service层方法，查询到商品的信息
//        Product product = productService.findById(productId);
//        //开始设计我们需要埋的数据     用户的userID从哪里来
//        User user = (User) session.getAttribute("user");
//        //String name, String phoneNumber, int productId, String title,
//        // Double shopPrice, String times
//        ShoppingLogs shoppingLogs = new ShoppingLogs(user.getName(),user.getPhone(),
//                product.getId(),product.getTitle(),product.getShopPrice(),TimeUtils.getTimes());
//        //输出 ShoppingLogs{name='pengfei zhang', phoneNumber='15035451125', productId=10, title='Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机',
//        // shopPrice=8888.0, times='2022-05-11 08:55:21'}
//        //为了将来处理方便  我们需要把埋好的数据变成json
//        String line = JSON.toJSONString(shoppingLogs);
//        //怎么把这个json落地到hdfs
//        hdfsService.logToHDFS(line);
//    }

    @ResponseBody
    @RequestMapping("/addHBase")
    public void addToHBase(int productId,HttpSession session) throws Exception {
        //调用service层方法，查询到商品的信息
        Product product = productService.findById(productId);
        //开始设计我们需要埋的数据     用户的userID从哪里来
        User user = (User) session.getAttribute("user");
        //String name, String phoneNumber, int productId, String title,
        // Double shopPrice, String times
        ShoppingLogs shoppingLogs = new ShoppingLogs(user.getName(),user.getPhone(),
                product.getId(),product.getTitle(),product.getShopPrice(),TimeUtils.getTimes());
        //输出 ShoppingLogs{name='pengfei zhang', phoneNumber='15035451125', productId=10, title='Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机',
        // shopPrice=8888.0, times='2022-05-11 08:55:21'}
        //为了将来处理方便  我们需要把埋好的数据变成json
      //  String line = JSON.toJSONString(shoppingLogs);
        //怎么把这个json落地到HBase
        hBaseService.dataToHBase(shoppingLogs);
    }

    /**
     * 移除购物车
     *
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCart.do")
    public ResultBean<Boolean> delToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.remove(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * 查看购物车商品
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listCart.do")
    public ResultBean<List<OrderItem>> listCart(HttpServletRequest request) throws Exception {
        List<OrderItem> orderItems = shopCartService.listCart(request);
        return new ResultBean<>(orderItems);
    }


}
