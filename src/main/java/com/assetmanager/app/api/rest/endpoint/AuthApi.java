package com.assetmanager.app.api.rest.endpoint;

import com.assetmanager.app.bean.AuthBeanI;
import com.assetmanager.app.dto.UserDto;
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

    @Inject
    JWTUtil jwtUtil;

    ModelMapper modelMapper = new ModelMapper();

    @Context
    private HttpServletRequest servletRequest;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(User loginUser) {
        User authUser = authBean.authenticate(loginUser);
        if (authUser != null && StringUtils.isNotBlank(authUser.getUsername())) {
            String token = jwtUtil.generateToken(authUser);
            UserDto user = modelMapper.map(authUser, UserDto.class);

            // to help migrate pages using servlets
            HttpSession httpSession = servletRequest.getSession(true);
            httpSession.setAttribute("loggedInId", new Date().getTime() + "");
            httpSession.setAttribute("username", loginUser.getUsername());
            httpSession.setAttribute("role", loginUser.getUserRole());

            return Response.ok(user).header("Authorization", token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid User Login!").build();
    }
}
