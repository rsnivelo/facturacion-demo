package com.santo.facturacion.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPages;
	private int elementsByPage;
	private int actualPage;
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();

		elementsByPage = page.getSize();
		totalPages = page.getTotalPages();
		actualPage = page.getNumber() + 1;

		int desde, hasta;
		if (totalPages <= elementsByPage) {
			desde = 1;
			hasta = totalPages;
		} else {
			if (actualPage <= elementsByPage / 2) {
				desde = 1;
				hasta = elementsByPage;
			} else if (actualPage >= totalPages - elementsByPage / 2) {
				desde = totalPages - elementsByPage + 1;
				hasta = elementsByPage;
			} else {
				desde = actualPage - elementsByPage / 2;
				hasta = elementsByPage;
			}
		}

		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, actualPage == desde + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getActualPage() {
		return actualPage;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
