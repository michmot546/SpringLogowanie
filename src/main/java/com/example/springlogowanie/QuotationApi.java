package com.example.springlogowanie;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationApi {
    private List<Quotation> quotationList;

    public QuotationApi(){
        this.quotationList=new ArrayList<>();
        quotationList.add(new Quotation("tu jest zanisoko","Tomasz B"));
        quotationList.add(new Quotation("yhm","Bartłomiej Spleśniały"));
        quotationList.add(new Quotation("co chcesz?","Piotr Świder"));
    }

    @GetMapping("/api/")
    public List<Quotation> getQuotationList(){
        return quotationList;
    }

    @PostMapping("/api/")
    public boolean addQuotation(@RequestBody Quotation quotation){
        return quotationList.add(quotation);
    }

    @DeleteMapping("/api/")
    public void deleteQuotation(@RequestParam int index){
        quotationList.remove(index);
    }
}
