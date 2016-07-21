


<!-- ==================== PAGE CONTENT ==================== -->
<div class="content">

  <!-- ==================== BREADCRUMBS / DATETIME ==================== -->
  <ul class="breadcrumb">
    <li><i class="icon-home"></i><a href="index.html"> Home</a> <span class="divider"><i class="icon-angle-right"></i></span></li>
    <li>Interface <span class="divider"><i class="icon-angle-right"></i></span></li>
    <li class="active">UI Elements</li>
    <li class="moveDown pull-right">
      <span class="time"></span>
      <span class="date"></span>
    </li>
  </ul>
  <!-- ==================== END OF BREADCRUMBS / DATETIME ==================== -->

  <!-- ==================== WIDGETS CONTAINER ==================== -->
  <div class="container-fluid">
    <!-- ==================== MASTER ACTIONS ROW ==================== -->
    <div class="row-fluid">
      <!-- ==================== MASTER ACTIONS LIST ==================== -->
      <ul class="masterActions">
        <li class="active">
          <i class="icon-envelope redText"></i>
          <h1 class="redText">Check & send</h1>
          <p>new messages</p>
          <div class="notifyCircle red">12</div>
        </li>
        <li>
          <i class="icon-ok-sign cyanText"></i>
          <h1 class="cyanText">Check & add</h1>
          <p>new tasks</p>
          <div class="notifyCircle cyan">3</div>
        </li>
        <li>
          <i class="icon-shopping-cart greenText"></i>
          <h1 class="greenText">Check & manage</h1>
          <p>new orders</p>
          <div class="notifyCircle green">254</div>
        </li>
        <li>
          <i class="icon-bar-chart orangeText"></i>
          <h1 class="orangeText">Check</h1>
          <p>statistics</p>
        </li>
        <li>
          <i class="icon-inbox greyText"></i>
          <h1 class="greyText">Check & upload</h1>
          <p>new files</p>
          <div class="notifyCircle grey">2</div>
        </li>
      </ul>
      <!-- ==================== END OF MASTER ACTIONS LIST ==================== -->
    </div>
    <!-- ==================== END OF MASTER ACTIONS ROW ==================== -->
    <!-- ==================== ALERTS ROW ==================== -->
    <div class="row-fluid">
      <div class="alert">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Warning!</strong> Best check yo self, you're not looking too good.
      </div>
      <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Well done!</strong> You successfully read this important alert message.
      </div>
      <div class="alert alert-error">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Oh snap!</strong> Change a few things up and try submitting again.
      </div>
      <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
      </div>
    </div>
    <!-- ==================== END OF ALERTS ROW ==================== -->
    <!-- ==================== DIVIDER ROW ==================== -->
    <div class="row-fluid">
      <div class="span12 contentDivider"></div>
    </div>
    <!-- ==================== END OF DIVIDER ROW ==================== -->
    <div class="row-fluid">

      <!-- ==================== LEFT HALF ==================== -->
      <div class="span6">
        <!-- ==================== MODALS HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-comment"></i><h2>Modal Dialogs</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF MODALS HEADLINE ==================== -->

        <!-- ==================== MODALS FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <div id="modalDialog" class="modal modalDialog hide fade" tabindex="-1" role="dialog" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h3>Modal header</h3>
              </div>
              <div class="modal-body">
                <p>One fine body…</p>
              </div>
            </div>
            <div id="modalConfirm" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h3>Modal header</h3>
              </div>
              <div class="modal-body">
                <form class="form-horizontal contentForm">
                  <div class="control-group">
                    <label class="control-label">Normal input field</label>
                    <div class="controls">
                      <input type="text" class="span10">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">Password input field</label>
                    <div class="controls">
                      <input type="password" class="span10">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">Input with placeholder</label>
                    <div class="controls">
                      <input type="text" class="span10" placeholder="This is a placeholder...">
                    </div>
                  </div>
                  <div class="control-group last">
                    <label class="control-label">Normal textarea</label>
                    <div class="controls">
                      <textarea class="span10"></textarea>
                    </div>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary">Save changes</button>
              </div>
            </div>
            <a href="#modalDialog" role="button" class="btn btn-primary" data-toggle="modal">Open Modal Dialog</a>
            <a href="#modalConfirm" role="button" class="btn btn-danger" data-toggle="modal">Open Modal Confirmation</a>
          </div>
        </div>
        <!-- ==================== END OF MODALS FLOATING BOX ==================== -->

        <!-- ==================== PROGRESS BARS HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-signal"></i><h2>Progress Bars</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF PROGRESS BARS HEADLINE ==================== -->

        <!-- ==================== PROGRESS BARS FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <h4>Basic Progress Bars</h4>
            <div class="progress progress-info">
              <div class="bar" style="width: 20%"></div>
            </div>
            <div class="progress progress-success">
              <div class="bar" style="width: 40%"></div>
            </div>
            <div class="progress progress-warning">
              <div class="bar" style="width: 60%"></div>
            </div>
            <div class="progress progress-danger">
              <div class="bar" style="width: 80%"></div>
            </div>
            <h4>Striped Progress Bars</h4>
            <div class="progress progress-info progress-striped">
              <div class="bar" style="width: 20%"></div>
            </div>
            <div class="progress progress-success progress-striped">
              <div class="bar" style="width: 40%"></div>
            </div>
            <div class="progress progress-warning progress-striped">
              <div class="bar" style="width: 60%"></div>
            </div>
            <div class="progress progress-danger progress-striped">
              <div class="bar" style="width: 80%"></div>
            </div>
            <div class="alert">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <strong>Warning!</strong> Not supported in IE.
            </div>
            <h4>Animated Striped Progress Bars</h4>
            <div class="progress progress-info progress-striped active">
              <div class="bar" style="width: 20%"></div>
            </div>
            <div class="progress progress-success progress-striped active">
              <div class="bar" style="width: 40%"></div>
            </div>
            <div class="progress progress-warning progress-striped active">
              <div class="bar" style="width: 60%"></div>
            </div>
            <div class="progress progress-danger progress-striped active">
              <div class="bar" style="width: 80%"></div>
            </div>
            <div class="alert">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <strong>Warning!</strong> Not supported in IE.
            </div>
            <h4>Stacked Progress Bars</h4>
            <div class="progress">
              <div class="bar bar-success" style="width: 35%;"></div>
              <div class="bar bar-warning" style="width: 20%;"></div>
              <div class="bar bar-danger" style="width: 10%;"></div>
            </div>
          </div>
        </div>
        <!-- ==================== END OF PROGRESS BARS FLOATING BOX ==================== -->

        <!-- ==================== TOOLTIPS HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-comments"></i><h2>Tooltips</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF TOOLTIPS HEADLINE ==================== -->

        <!-- ==================== TOOLTIPS FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <button id="topTooltip" class="btn" data-toggle="tooltip" data-placement="top" title="" data-original-title="Tooltip on top">Tooltip on top</button>
            <button id="rightTooltip" class="btn" data-toggle="tooltip" data-placement="right" title="" data-original-title="Tooltip on right">Tooltip on right</button>
            <button id="bottomTooltip" class="btn" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Tooltip on bottom">Tooltip on bottom</button>
            <button id="leftTooltip" class="btn" data-toggle="tooltip" data-placement="left" title="" data-original-title="Tooltip on left">Tooltip on left</button>
          </div>
        </div>
        <!-- ==================== END OF TOOLTIPS FLOATING BOX ==================== -->

        <!-- ==================== PAGINATION HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-book"></i><h2>Pagination</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF PAGINATION HEADLINE ==================== -->

        <!-- ==================== PAGINATION FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <div class="span6">
              <h4>Default Pagination</h4>
              <h6>Large Size</h6>
              <div class="pagination pagination-large">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
              <h6>Default Size</h6>
              <div class="pagination">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
              <h6>Small Size</h6>
              <div class="pagination pagination-small">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
              <h6>Mini Size</h6>
              <div class="pagination pagination-mini">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
            </div>
            <div class="span6">
              <h4>Custom Pagination</h4>
              <h6>Large Size</h6>
              <div class="pagination pagination-large custom">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
              <h6>Default Size</h6>
              <div class="pagination custom">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
              <h6>Small Size</h6>
              <div class="pagination pagination-small custom">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
              <h6>Mini Size</h6>
              <div class="pagination pagination-mini custom">
                <ul>
                  <li><a href="#">«</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">»</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <!-- ==================== END OF PAGINATION FLOATING BOX ==================== -->

      </div>
      <!-- ==================== END OF LEFT HALF ==================== -->
      <!-- ==================== RIGHT HALF ==================== -->
      <div class="span6">

        <!-- ==================== NOTIFICATIONS HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-info-sign"></i><h2>Notifications</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF NOTIFICATIONS HEADLINE ==================== -->

        <!-- ==================== NOTIFICATIONS FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <div class="alert">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h4>Warning!</h4>
              Best check yo self, you're not looking too good.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </div>
            <div class="alert alert-success">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h4>Well done!</h4>
              You successfully read this important alert message.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </div>
            <div class="alert alert-error">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h4>Oh snap!</h4>
              Change a few things up and try submitting again.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </div>
            <div class="alert alert-info">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <h4>Heads up!</h4>
              THIS alert needs your attention, but it's not super important.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </div>
          </div>
        </div>
        <!-- ==================== END OF NOTIFICATIONS FLOATING BOX ==================== -->

        <!-- ==================== LABELS AND BADGES HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-asterisk"></i><h2>Labels & Badges</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF LABELS AND BADGES HEADLINE ==================== -->

        <!-- ==================== LABELS AND BADGES FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <h4>Labels</h4>
            <table class="table table-bordered table-striped">
              <thead>
              <tr>
                <th>Labels</th>
                <th>Markup</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>
                  <span class="label">Default</span>
                </td>
                <td>
                  <code>&lt;span class="label"&gt;Default&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  <span class="label label-success">Success</span>
                </td>
                <td>
                  <code>&lt;span class="label label-success"&gt;Success&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  <span class="label label-warning">Warning</span>
                </td>
                <td>
                  <code>&lt;span class="label label-warning"&gt;Warning&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  <span class="label label-important">Important</span>
                </td>
                <td>
                  <code>&lt;span class="label label-important"&gt;Important&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  <span class="label label-info">Info</span>
                </td>
                <td>
                  <code>&lt;span class="label label-info"&gt;Info&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  <span class="label label-inverse">Inverse</span>
                </td>
                <td>
                  <code>&lt;span class="label label-inverse"&gt;Inverse&lt;/span&gt;</code>
                </td>
              </tr>
              </tbody>
            </table>
            <h4>Badges</h4>
            <table class="table table-bordered table-striped">
              <thead>
              <tr>
                <th>Name</th>
                <th>Example</th>
                <th>Markup</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>
                  Default
                </td>
                <td>
                  <span class="badge">1</span>
                </td>
                <td>
                  <code>&lt;span class="badge"&gt;1&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  Success
                </td>
                <td>
                  <span class="badge badge-success">2</span>
                </td>
                <td>
                  <code>&lt;span class="badge badge-success"&gt;2&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  Warning
                </td>
                <td>
                  <span class="badge badge-warning">4</span>
                </td>
                <td>
                  <code>&lt;span class="badge badge-warning"&gt;4&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  Important
                </td>
                <td>
                  <span class="badge badge-important">6</span>
                </td>
                <td>
                  <code>&lt;span class="badge badge-important"&gt;6&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  Info
                </td>
                <td>
                  <span class="badge badge-info">8</span>
                </td>
                <td>
                  <code>&lt;span class="badge badge-info"&gt;8&lt;/span&gt;</code>
                </td>
              </tr>
              <tr>
                <td>
                  Inverse
                </td>
                <td>
                  <span class="badge badge-inverse">10</span>
                </td>
                <td>
                  <code>&lt;span class="badge badge-inverse"&gt;10&lt;/span&gt;</code>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!-- ==================== END OF LABELS AND BADGES FLOATING BOX ==================== -->

        <!-- ==================== GROWL NOTIFICATIONS HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-info-sign"></i><h2>Growl Notifications (using jGrowl)</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF GROWL NOTIFICATIONS HEADLINE ==================== -->

        <!-- ==================== GROWL NOTIFICATIONS FLOATING BOX ==================== -->
        <div class="floatingBox examples">
          <div class="container-fluid">
            <button class="btn" id="defaultGrowl">Default growl notification</button>
            <button class="btn btn-info" id="stickyGrowl">Sticky growl notification</button>
            <button class="btn btn-danger" id="headerGrowl">Growl notification with header</button>
            <button class="btn btn-success" id="longerGrowl">Growl notification with longer live</button>
            <button class="btn btn-inverse" id="specialGrowl">Growl notification with callback</button>
          </div>
        </div>
        <!-- ==================== END OF GROWL NOTIFICATIONS FLOATING BOX ==================== -->

        <!-- ==================== INLINE SELECT HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-check"></i><h2>Inline Select</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF INLINE SELECT HEADLINE ==================== -->
        <!-- ==================== INLINE SELECT FLOATING BOX ==================== -->
        <div class="floatingBox inlineSelect inline danger">
          <div class="container-fluid">
            <div class="control-group">
              <label class="check_boxes optional control-label">Options: </label>
              <div class="controls">
                <label class="checkbox checked">
                  <input class="css-checkbox" type="checkbox" checked>
                  <span class="css-label">Option 1</span>
                </label>
                <label class="checkbox checked">
                  <input class="css-checkbox" type="checkbox" checked>
                  <span class="css-label">Option 2</span>
                </label>
                <label class="checkbox">
                  <input class="css-checkbox"type="checkbox">
                  <span class="css-label">Option 3</span>
                </label>
                <label class="checkbox">
                  <input class="css-checkbox"type="checkbox">
                  <span class="css-label">Option 4</span>
                </label>
              </div>
            </div>
          </div>
        </div>
        <!-- ==================== END OF INLINE SELECT FLOATING BOX ==================== -->

        <!-- ==================== ROW SELECT HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-check"></i><h2>Row Select</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF ROW SELECT HEADLINE ==================== -->
        <!-- ==================== ROW SELECT FLOATING BOX ==================== -->
        <div class="floatingBox inlineSelect rowSelect danger">
          <div class="container-fluid">
            <div class="control-group">
              <label class="check_boxes optional control-label">Options: </label>
              <div class="controls">
                <label class="checkbox checked">
                  <input class="css-checkbox" type="checkbox" checked>
                  <span class="css-label">Option 1</span>
                </label>
                <label class="checkbox checked">
                  <input class="css-checkbox" type="checkbox" checked>
                  <span class="css-label">Option 2</span>
                </label>
                <label class="checkbox">
                  <input class="css-checkbox"type="checkbox">
                  <span class="css-label">Option 3</span>
                </label>
                <label class="checkbox">
                  <input class="css-checkbox"type="checkbox">
                  <span class="css-label">Option 4</span>
                </label>
              </div>
            </div>
          </div>
        </div>
        <!-- ==================== END OF ROW SELECT FLOATING BOX ==================== -->

      </div>
      <!-- ==================== END OF RIGHT HALF ==================== -->
    </div>
    <div class="row-fluid">
      <div class="span12">
        <!-- ==================== GROWL NOTIFICATIONS HEADLINE ==================== -->
        <div class="containerHeadline">
          <i class="icon-tags"></i><h2>Other elements</h2>
          <div class="controlButton pull-right"><i class="icon-remove removeElement"></i></div>
          <div class="controlButton pull-right"><i class="icon-caret-down minimizeElement"></i></div>
        </div>
        <!-- ==================== END OF GROWL NOTIFICATIONS HEADLINE ==================== -->

        <!-- ==================== GROWL NOTIFICATIONS FLOATING BOX ==================== -->
        <div class="floatingBox">
          <div class="container-fluid">
            <div class="span6">
              <h4>Slider</h4>
              <div id="sliderOne" class="span6"></div>
              <h4>Range Slider</h4>
              <div id="sliderTwo" class="span6"></div>
              <h4>Vertical Sliders</h4>
              <div id="eg">
                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-13" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">

                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-11" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">

                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-6" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">

                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-4" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">

                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-6" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">

                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-11" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">

                <input type="text" class="span2" value="" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-14" data-slider-orientation="vertical" data-slider-selection="after" data-slider-tooltip="hide">
              </div>
            </div>
            <div class="span6">
              <h4>Autocomplete</h4>
              <input type="text" class="span6" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Connecticut&quot;,&quot;Delaware&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]">
              <h4>Datepicker</h4>
              <input id="datepickerField" type="text" class="span10" value="04/26/2013">
              <h4>Menu</h4>
              <div class="dropdown clearfix example">
                <ul class="dropdown-menu">
                  <li><a tabindex="-1" href="#">Action</a></li>
                  <li><a tabindex="-1" href="#">Another action</a></li>
                  <li><a tabindex="-1" href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-submenu">
                    <a tabindex="-1" href="#">More options</a>
                    <ul class="dropdown-menu">
                      <li><a tabindex="-1" href="#">Second level link</a></li>
                      <li><a tabindex="-1" href="#">Second level link</a></li>
                      <li><a tabindex="-1" href="#">Second level link</a></li>
                      <li><a tabindex="-1" href="#">Second level link</a></li>
                      <li><a tabindex="-1" href="#">Second level link</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <!-- ==================== END OF GROWL NOTIFICATIONS FLOATING BOX ==================== -->
      </div>
    </div>


  </div>
  <!-- ==================== END OF WIDGETS CONTAINER ==================== -->
</div>
<!-- ==================== END OF PAGE CONTENT ==================== -->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
<script src="js/vendor/bootstrap-slider.js"></script>                   <!-- bootstrap slider plugin -->
<script src="js/vendor/jquery.sparkline.min.js"></script>               <!-- small charts plugin -->
<script src="js/vendor/bootstrap-datepicker.js"></script>               <!-- datepicker plugin -->
<script src="js/vendor/jquery.jgrowl.min.js"></script>                  <!-- jgrowl plugin -->


<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/thekamarel.min.js"></script>                            <!-- main project js file -->

<script>

  $(function () {

    $('#topTooltip, #rightTooltip, #bottomTooltip, #leftTooltip').tooltip();

    $("#defaultGrowl").click(function() {
      $.jGrowl("Hello world!");
    });

    $("#stickyGrowl").click(function() {
      $.jGrowl("Stick this!", { sticky: true });
    });

    $("#headerGrowl").click(function() {
      $.jGrowl("A message with a header", { header: 'Important' });
    });

    $("#longerGrowl").click(function() {
      $.jGrowl("A message that will live a little longer.", { life: 10000 });
    });

    $("#specialGrowl").click(function() {
      $.jGrowl("A message with a beforeClose callback and a different opening animation.", {
        beforeClose: function(e,m) {
          alert('About to close this notification!');
        },
        animateOpen: {
          height: 'show'
        }
      });
    });

    $('#sliderOne').slider({
      min: 0,
      max: 100,
      step: 1
    });

    $('#sliderTwo').slider({
      value: [40,60]
      ,min: 0
      ,max: 100
      ,step: 1
    });

    $('#eg input').slider().css({opacity : 0});

    $('#datepickerField').datepicker();

    var setLabelWidth = function() {
      var parentWidth = $('.inlineSelect.inline .control-group').width();
      var childrenLength = $('.inlineSelect.inline label').length;

      $('.inlineSelect.inline label').css('width', ((parentWidth / childrenLength) - 12) + 'px');
    }

    setLabelWidth();

    $(window).resize(function() {
      setLabelWidth();
    });

    $('.inlineSelect :radio, .inlineSelect :checkbox').change(function() {
      $(this).parent().toggleClass("checked", this.checked);
    });

  })


</script>
</body>
</html>
