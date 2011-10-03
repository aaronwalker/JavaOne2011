package com.base2.javaone.demo.view;
import java.util.List;
import java.util.ArrayList;
   
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.base2.javaone.demo.domain.Customer;
import org.metawidget.forge.navigation.MenuItem;
import org.metawidget.forge.persistence.PaginationHelper;
import org.metawidget.forge.persistence.PersistenceUtil;
import org.jboss.seam.transaction.Transactional;

@Transactional @Named
@RequestScoped
public class CustomerBean extends PersistenceUtil implements MenuItem
{
   private static final long serialVersionUID = 1L;
   
   private List<Customer> list = null;
   private Customer customer = new Customer();
   private long id = 0;
   private PaginationHelper<Customer> pagination;		
   
   public Class<?> getItemType()
   {
      return Customer.class;
   }
   
   public String getLiteralPath()
   {
      return null;
   }

   public String getLabel()
   {
      return null;
   }

   public void load()
   {
      customer = findById(Customer.class, id);
   }
   
   public String create()
   {
      create(customer);
      return "view?faces-redirect=true&id=" + customer.getId();
   }

   public String delete()
   {
      delete(customer);
      return "list?faces-redirect=true";
   }

   public String save()
   {
      save(customer);
      return "view?faces-redirect=true&id=" + customer.getId();
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
      if(id>0){
			load();
	  }	
   }
   
   public Customer getCustomer()
   {
      return customer;
   }

   public void setCustomer(Customer customer)
   {
      this.customer = customer;
   }

   public List<Customer> getList()
   {
      if(list == null)
      {
         list = getPagination().createPageDataModel();
      }
      return list;
   }

   public void setList(List<Customer> list)
   {
      this.list = list;
   }
  
   public PaginationHelper<Customer> getPagination() 
   {
		if (pagination == null) 
		{
			pagination = new PaginationHelper<Customer>(10) 
			{
				@Override
				public int getItemsCount() {
					return count(Customer.class);
				}

				@Override
				public List<Customer> createPageDataModel() 
				{
					return new ArrayList<Customer>(findAll(Customer.class,
							 getPageFirstItem(), getPageSize() ));
				}
			};
		}
		return pagination;
	}

   public void setPagination(final PaginationHelper<Customer> helper)
   {
      pagination = helper;
   }
}