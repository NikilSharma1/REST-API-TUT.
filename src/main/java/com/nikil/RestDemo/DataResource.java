package com.nikil.RestDemo;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("dataresource")
public class DataResource {

	DataRepository depoRepository = new DataRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Data> getAllData() {
		// System.out.println("getAlldata is called ");
		return depoRepository.getList();
	}

	@GET
	@Path("data/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Data getData(@PathParam("id") int id) {
		return depoRepository.getData(id);
	}

	@POST
	@Path("data")
	// @Produces(MediaType.APPLICATION_XML)
	public void createData(Data d) {
		depoRepository.insertData(d);
	}

	@PUT
	@Path("data")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateData(Data d) {
		if (depoRepository.getData(d.getId()).getId() != 0) {
			depoRepository.UpdateData(d);
		} else {
			System.out.println("This data doesn't exists hence can't be update , so it will be created");
			depoRepository.insertData(d);
		}
	}

	@PUT
	@Path("data/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void DeleteData(@PathParam("id") int id) {
		if (depoRepository.getData(id).getId() != 0) {
			depoRepository.DeleteData(id);
		} else {
			System.out.println("This data doesn't exists hence can't be deleted ");
		}
	}

}
