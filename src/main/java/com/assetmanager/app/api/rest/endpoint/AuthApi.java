package com.assetmanager.app.api.rest.endpoint;

import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.bean.AuthBeanI;
import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.dto.UserDto;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.auth.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/auth")
public class AuthApi extends BaseRestApi {
    @EJB
    AuthBeanI authBean;

    @EJB
    UserBeanI userBean;


    @EJB
    AssigneeBeanI assigneeBean;
    @Inject
    JWTUtil jwtUtil;

    ModelMapper modelMapper = new ModelMapper();


    @Context
    private HttpServletRequest servletRequest;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(User loginUser) {
        User authUser = authBean.authenticate(loginUser);
        if (authUser != null && StringUtils.isNotBlank(authUser.getUsername())) {
            String token = jwtUtil.generateToken(authUser);
            UserDto user = modelMapper.map(authUser, UserDto.class);


            //Some data to work with APIs
            Assignee assignee = assigneeBean.getAssigneeByEmail(authUser.getEmail());
            if (assignee != null)
                user.setAssigneeId(assignee.getId());


            // to help migrate pages using servlets
            HttpSession httpSession = servletRequest.getSession(true);
            httpSession.setAttribute("loggedInId", new Date().getTime() + "");
            httpSession.setAttribute("username", user.getUsername());
            httpSession.setAttribute("role", user.getRole());

            return Response.ok(user).header("Authorization", token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid User Login!").build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User signUpUser) {
        Boolean newUserCreated = userBean.registerUser(signUpUser);
        if (newUserCreated) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(404).entity("Invalid User Details!").build();
    }

}
