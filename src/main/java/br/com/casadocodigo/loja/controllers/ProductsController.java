package br.com.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.FileSaver;
import br.com.casadocodigo.loja.models.Product;


@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	/*@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}*/
	
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		System.out.println(summary.getName() + ";"
				+summary.getOriginalFilename());
		
		if(bindingResult.hasErrors()) {			
			return form(product);			
		}
		
		String webPath =
				fileSaver.write("uploaded-images",summary);
				product.setSummaryPath(webPath);
				productDAO.save(product);
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:/produtos");
	}
	
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());			
		
		return modelAndView;
	}

	@RequestMapping("/form")
	public ModelAndView form(Product product) {

		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());

		return modelAndView;
	}

}
