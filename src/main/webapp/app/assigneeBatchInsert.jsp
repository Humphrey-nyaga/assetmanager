<div class="custom-file">
  <form method="post" action="assigneeBatchUpload" enctype="multipart/form-data">
    <input type="file" class="custom-file-input" id="customFile" name="customFile">
    <label class="custom-file-label" for="customFile">Choose file</label>
    <button type="button" onclick="uploadFile()">Upload</button>
  </form>
</div>

<script>
  async function uploadFile() {
    let formData = new FormData();
    formData.append("customFile", document.getElementById('customFile').files[0]);
    await fetch('fileuploadservlet', {
      method: "POST",
      body: formData
    });
    alert('The file upload with Ajax and Java was a success!');
  }
</script>

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUploadServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    /* Receive file uploaded to the Servlet from the HTML5 form */
    Part filePart = request.getPart("customFile");
    String fileName = filePart.getSubmittedFileName();
    for (Part part : request.getParts()) {
      part.write("C:\\upload\\" + fileName);
    }
    response.getWriter().print("The file uploaded sucessfully.");
  }

}
