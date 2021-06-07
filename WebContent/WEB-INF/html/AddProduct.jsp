   <!-- Start Login Register Area -->
        <div class="htc__login__register bg__white ptb--150">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <ul class="login__register__menu" role="tablist">
                            <li role="presentation" class="register active"><p role="tab" data-toggle="tab">Agregar producto</p></li>
                        </ul>
                    </div>
                </div>
                <!-- Start Login Register Content -->
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="htc__login__register__wrap">
                            <!-- Start Single Content -->
                           	<div id="login" role="tabpanel" class="single__tabs__panel tab-pane fade in active">
                                <form class="login" enctype="multipart/form-data">
								   
								    <input type="file" name="file" />					       	
                                    <input class="input" type="text" placeholder="Nombre producto" name="name">
                                    <input class="input" type="text" placeholder="Descripcion del producto" name="description">
                                    <input class="input" type="number" step="1"    min="1" placeholder="Numero a la venta" name="numero">
                                    <input class="input" type="number" pattern="^[0-9]{0,12}([,][0-9]{2,2})?$" step="any" min="0.01" placeholder="Precio a la venta por unidad" name="precio">
									
									<p>Moneda : </p>
										<select class="input" name="moneda">
										<option selected>Euro</option>
										<option>Dolar</option>
										<option>Libra</option>
										<option>Yen</option>
									</select>
									
									<p>Categoria : </p>
										<select class="input" name="categoria">
											<c:forEach items="${categories}" var="category">
			          		        			<option>${category.name}</option>
											</c:forEach>
									</select>
									
									<p>Envio rápido (24h)</p>
									<label for="si">Si</label><br>
									<input class="radio" type="radio" id="si" name="rapido" value="Si">
									<label for="no">No</label><br>
									<input class="radio" type="radio" id="no" name="rapido" value="No">
									
                                    <div class="htc__login__btn">
                                    	<input type="submit" value="Añadir">
                                	</div>
                                </form>

                               
                            </div>
                            <!-- End Single Content -->
                        </div>
                    </div>
                </div>
                <!-- End Login Register Content -->
            </div>
        </div>
        <!-- End Login Register Area -->