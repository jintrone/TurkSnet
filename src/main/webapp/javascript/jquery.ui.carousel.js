
    

  

<!DOCTYPE html>
<html>
  <head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <script type="text/javascript">var NREUMQ=[];NREUMQ.push(["mark","firstbyte",new Date().getTime()]);</script>
        <title>js/jquery.ui.carousel.js at master from richardscarrott/jquery-ui-carousel - GitHub</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />

    
    

    <meta content="authenticity_token" name="csrf-param" />
<meta content="1889ffab6bd7721faac28e442fe176d257773e0f" name="csrf-token" />

    <link href="https://a248.e.akamai.net/assets.github.com/6bd15e682cebb90bd9ca66bfdb3296f117072641/stylesheets/bundle_github.css" media="screen" rel="stylesheet" type="text/css" />
    

    <script type="text/javascript">
      if (typeof console == "undefined" || typeof console.log == "undefined")
        console = { log: function() {} }
    </script>
    <script src="https://a248.e.akamai.net/assets.github.com/javascripts/jquery/jquery-1.6.1.min.js" type="text/javascript"></script>
    <script src="https://a248.e.akamai.net/assets.github.com/817e52c598b12c0e23c374ec490beaa1085192a1/javascripts/bundle_failbot.js" type="text/javascript"></script>


    <script type="text/javascript" charset="utf-8">
      var GitHub = {
        assetHost: 'https://a248.e.akamai.net/assets.github.com'
      }
      var github_user = 'jintrone'

      
    </script>
    <script src="https://a248.e.akamai.net/assets.github.com/ad23f261a1b93dfd00e6954a67d9148313a96444/javascripts/bundle_github.js" type="text/javascript"></script>


    
    <script type="text/javascript" charset="utf-8">
      if (GitHub.spy) {
        GitHub.spy({repo: "richardscarrott/jquery-ui-carousel"});
      }
    </script>

    
  <link rel='permalink' href='/richardscarrott/jquery-ui-carousel/blob/d87446d14e1b87bb1d96c43445da7246c2f3d560/js/jquery.ui.carousel.js'>

  <link href="https://github.com/richardscarrott/jquery-ui-carousel/commits/master.atom" rel="alternate" title="Recent Commits to jquery-ui-carousel:master" type="application/atom+xml" />

    

    <meta name="description" content="jquery-ui-carousel - Re-write of jQuery Carousel using jQuery UI's Widget Factory" />
    <script type="text/javascript">
      GitHub.nameWithOwner = GitHub.nameWithOwner || "richardscarrott/jquery-ui-carousel";
      GitHub.currentRef = 'master';
      GitHub.commitSHA = "d87446d14e1b87bb1d96c43445da7246c2f3d560";
      GitHub.currentPath = 'js/jquery.ui.carousel.js';
      GitHub.masterBranch = "master";

      
    </script>
  

        <script type="text/javascript">
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-3769691-2']);
      _gaq.push(['_setDomainName', 'none']);
      _gaq.push(['_trackPageview']);
      _gaq.push(['_trackPageLoadTime']);
      (function() {
        var ga = document.createElement('script');
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        ga.setAttribute('async', 'true');
        document.documentElement.firstChild.appendChild(ga);
      })();
    </script>

    
  </head>

  

  <body class="logged_in page-blob  env-production">
    

    

    

    <div class="subnavd" id="main">
      <div id="header" class="true">
          <a class="logo boring" href="https://github.com/">
            
            <img alt="github" class="default" height="45" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov6.png" />
            <!--[if (gt IE 8)|!(IE)]><!-->
            <img alt="github" class="hover" height="45" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov6-hover.png" />
            <!--<![endif]-->
          </a>

        
          





  
    <div class="userbox">
      <div class="avatarname">
        <a href="https://github.com/jintrone"><img src="https://secure.gravatar.com/avatar/966eee960f30d45d85559a5cf0330b91?s=140&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" alt="" width="20" height="20"  /></a>
        <a href="https://github.com/jintrone" class="name">jintrone</a>

        
        
      </div>
      <ul class="usernav">
        <li><a href="https://github.com/">Dashboard</a></li>
        <li>
          
          <a href="https://github.com/inbox">Inbox</a>
          <a href="https://github.com/inbox" class="unread_count new js-inbox-count">1</a>
        </li>
        <li><a href="https://github.com/account">Account Settings</a></li>
        <li><a href="/logout">Log Out</a></li>
      </ul>
    </div><!-- /.userbox -->
  


        
        <div class="topsearch">
  
    <form action="/search" id="top_search_form" method="get">
      <a href="/search" class="advanced-search tooltipped downwards" title="Advanced Search">Advanced Search</a>
      <div class="search placeholder-field js-placeholder-field">
        <label class="placeholder" for="global-search-field">Search…</label>
        <input type="text" class="search my_repos_autocompleter" id="global-search-field" name="q" results="5" /> <input type="submit" value="Search" class="button" />
      </div>
      <input type="hidden" name="type" value="Everything" />
      <input type="hidden" name="repo" value="" />
      <input type="hidden" name="langOverride" value="" />
      <input type="hidden" name="start_value" value="1" />
    </form>
    <ul class="nav">
      <li><a href="/explore">Explore GitHub</a></li>
      <li><a href="https://gist.github.com">Gist</a></li>
      
      <li><a href="/blog">Blog</a></li>
      
      <li><a href="http://help.github.com">Help</a></li>
    </ul>
  
</div>

      </div>

      
      
        
    <div class="site">
      <div class="pagehead repohead vis-public    instapaper_ignore readability-menu">

      

      <div class="title-actions-bar">
        <h1>
          <a href="/richardscarrott">richardscarrott</a> / <strong><a href="/richardscarrott/jquery-ui-carousel">jquery-ui-carousel</a></strong>
          
          
        </h1>

        
    <ul class="actions">
      

      
        <li class="for-owner" style="display:none"><a href="/richardscarrott/jquery-ui-carousel/admin" class="minibutton btn-admin "><span><span class="icon"></span>Admin</span></a></li>
        <li>
          <a href="/richardscarrott/jquery-ui-carousel/toggle_watch" class="minibutton btn-watch " id="watch_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', '1889ffab6bd7721faac28e442fe176d257773e0f'); f.appendChild(s);f.submit();return false;" style="display:none"><span><span class="icon"></span>Watch</span></a>
          <a href="/richardscarrott/jquery-ui-carousel/toggle_watch" class="minibutton btn-watch " id="unwatch_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', '1889ffab6bd7721faac28e442fe176d257773e0f'); f.appendChild(s);f.submit();return false;" style="display:none"><span><span class="icon"></span>Unwatch</span></a>
        </li>
        
          
            <li class="for-notforked" style="display:none"><a href="/richardscarrott/jquery-ui-carousel/fork" class="minibutton btn-fork " id="fork_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', '1889ffab6bd7721faac28e442fe176d257773e0f'); f.appendChild(s);f.submit();return false;"><span><span class="icon"></span>Fork</span></a></li>
            <li class="for-hasfork" style="display:none"><a href="#" class="minibutton btn-fork " id="your_fork_button"><span><span class="icon"></span>Your Fork</span></a></li>
          

          <li id='pull_request_item' class='nspr' style='display:none'><a href="/richardscarrott/jquery-ui-carousel/pull/new/master" class="minibutton btn-pull-request "><span><span class="icon"></span>Pull Request</span></a></li>
        
      
      
      <li class="repostats">
        <ul class="repo-stats">
          <li class="watchers"><a href="/richardscarrott/jquery-ui-carousel/watchers" title="Watchers" class="tooltipped downwards">21</a></li>
          <li class="forks"><a href="/richardscarrott/jquery-ui-carousel/network" title="Forks" class="tooltipped downwards">5</a></li>
        </ul>
      </li>
    </ul>

      </div>

        
  <ul class="tabs">
    <li><a href="/richardscarrott/jquery-ui-carousel" class="selected" highlight="repo_source">Source</a></li>
    <li><a href="/richardscarrott/jquery-ui-carousel/commits/master" highlight="repo_commits">Commits</a></li>
    <li><a href="/richardscarrott/jquery-ui-carousel/network" highlight="repo_network">Network</a></li>
    <li><a href="/richardscarrott/jquery-ui-carousel/pulls" highlight="repo_pulls">Pull Requests (0)</a></li>

    

    
      
      <li><a href="/richardscarrott/jquery-ui-carousel/issues" highlight="issues">Issues (1)</a></li>
    

                <li><a href="/richardscarrott/jquery-ui-carousel/wiki" highlight="repo_wiki">Wiki (2)</a></li>
        
    <li><a href="/richardscarrott/jquery-ui-carousel/graphs" highlight="repo_graphs">Graphs</a></li>

    

    <li class="contextswitch nochoices">
      <span class="toggle leftwards" >
        <em>Branch:</em>
        <code>master</code>
      </span>
    </li>
  </ul>

  <div style="display:none" id="pl-description"><p><em class="placeholder">click here to add a description</em></p></div>
  <div style="display:none" id="pl-homepage"><p><em class="placeholder">click here to add a homepage</em></p></div>

  <div class="subnav-bar">
  
  <ul>
    <li>
      <a href="/richardscarrott/jquery-ui-carousel/branches" class="dropdown">Switch Branches (2)</a>
      <ul>
        
          
            <li><strong>master &#x2713;</strong></li>
            
          
          
            <li><a href="/richardscarrott/jquery-ui-carousel/blob/touch/js/jquery.ui.carousel.js" data-name="touch">touch</a></li>
          
        
      </ul>
    </li>
    <li>
      <a href="#" class="dropdown ">Switch Tags (2)</a>
              <ul>
                      
              <li><a href="/richardscarrott/jquery-ui-carousel/blob/v0.7.4/js/jquery.ui.carousel.js" data-name="v0.7.4">v0.7.4</a></li>
            
                      
              <li><a href="/richardscarrott/jquery-ui-carousel/blob/v0.6.4/js/jquery.ui.carousel.js" data-name="v0.6.4">v0.6.4</a></li>
            
                  </ul>
      
    </li>
    <li>
    
    <a href="/richardscarrott/jquery-ui-carousel/branches" class="manage">Branch List</a>
    
    </li>
  </ul>
</div>

  
  
  
  
  
  



        
    <div id="repo_details" class="metabox clearfix">
      <div id="repo_details_loader" class="metabox-loader" style="display:none">Sending Request&hellip;</div>

      
        <a href="/richardscarrott/jquery-ui-carousel/downloads" class="download-source" id="download_button" title="Download source, tagged packages and binaries."><span class="icon"></span>Downloads</a>
      

      <div id="repository_desc_wrapper">
      <div id="repository_description" rel="repository_description_edit">
        
          <p>Re-write of jQuery Carousel using jQuery UI's Widget Factory
            <span id="read_more" style="display:none">&mdash; <a href="#readme">Read more</a></span>
          </p>
        
      </div>

      <div id="repository_description_edit" style="display:none;" class="inline-edit">
        <form action="/richardscarrott/jquery-ui-carousel/admin/update" method="post"><div style="margin:0;padding:0"><input name="authenticity_token" type="hidden" value="1889ffab6bd7721faac28e442fe176d257773e0f" /></div>
          <input type="hidden" name="field" value="repository_description">
          <input type="text" class="textfield" name="value" value="Re-write of jQuery Carousel using jQuery UI's Widget Factory">
          <div class="form-actions">
            <button class="minibutton"><span>Save</span></button> &nbsp; <a href="#" class="cancel">Cancel</a>
          </div>
        </form>
      </div>

      
      <div class="repository-homepage" id="repository_homepage" rel="repository_homepage_edit">
        <p><a href="http://www.richardscarrott.co.uk/assets/javascript/jquery/ui-carousel/demo/" rel="nofollow">http://www.richardscarrott.co.uk/assets/javascript/jquery/ui-carousel/demo/</a></p>
      </div>

      <div id="repository_homepage_edit" style="display:none;" class="inline-edit">
        <form action="/richardscarrott/jquery-ui-carousel/admin/update" method="post"><div style="margin:0;padding:0"><input name="authenticity_token" type="hidden" value="1889ffab6bd7721faac28e442fe176d257773e0f" /></div>
          <input type="hidden" name="field" value="repository_homepage">
          <input type="text" class="textfield" name="value" value="http://www.richardscarrott.co.uk/assets/javascript/jquery/ui-carousel/demo/">
          <div class="form-actions">
            <button class="minibutton"><span>Save</span></button> &nbsp; <a href="#" class="cancel">Cancel</a>
          </div>
        </form>
      </div>
      </div>
      <div class="rule "></div>
      <div class="url-box">
  
    <ul class="native-clones">
      <li><a href="http://mac.github.com" class="minibutton btn-clone-in-mac "><span><span class="icon"></span> Clone in Mac</span></a></li>
    </ul>
  

  <ul class="clone-urls">
    
      
      <li class="http_clone_url"><a href="https://github.com/richardscarrott/jquery-ui-carousel.git" data-permissions="Read-Only">HTTP</a></li>
      <li class="public_clone_url"><a href="git://github.com/richardscarrott/jquery-ui-carousel.git" data-permissions="Read-Only">Git Read-Only</a></li>
    
    
  </ul>
  <input type="text" spellcheck="false" class="url-field" />
        <span style="display:none" id="clippy_953" class="url-box-clippy"></span>
      <span id="clippy_tooltip_clippy_953" class="clippy-tooltip tooltipped" title="copy to clipboard">
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
              width="14"
              height="14"
              class="clippy"
              id="clippy" >
      <param name="movie" value="https://a248.e.akamai.net/assets.github.com/flash/clippy.swf?v5"/>
      <param name="allowScriptAccess" value="always" />
      <param name="quality" value="high" />
      <param name="scale" value="noscale" />
      <param NAME="FlashVars" value="id=clippy_953&amp;copied=&amp;copyto=">
      <param name="bgcolor" value="#FFFFFF">
      <param name="wmode" value="opaque">
      <embed src="https://a248.e.akamai.net/assets.github.com/flash/clippy.swf?v5"
             width="14"
             height="14"
             name="clippy"
             quality="high"
             allowScriptAccess="always"
             type="application/x-shockwave-flash"
             pluginspage="http://www.macromedia.com/go/getflashplayer"
             FlashVars="id=clippy_953&amp;copied=&amp;copyto="
             bgcolor="#FFFFFF"
             wmode="opaque"
      />
      </object>
      </span>

  <p class="url-description"><strong>Read+Write</strong> access</p>
</div>

    </div>

    <div class="frame frame-center tree-finder" style="display:none">
      <div class="breadcrumb">
        <b><a href="/richardscarrott/jquery-ui-carousel">jquery-ui-carousel</a></b> /
        <input class="tree-finder-input" type="text" name="query" autocomplete="off" spellcheck="false">
      </div>

      
        <div class="octotip">
          <p>
            <a href="/richardscarrott/jquery-ui-carousel/dismiss-tree-finder-help" class="dismiss js-dismiss-tree-list-help" title="Hide this notice forever">Dismiss</a>
            <strong>Octotip:</strong> You've activated the <em>file finder</em> by pressing <span class="kbd">t</span>
            Start typing to filter the file list. Use <span class="kbd badmono">↑</span> and <span class="kbd badmono">↓</span> to navigate,
            <span class="kbd">enter</span> to view files.
          </p>
        </div>
      

      <table class="tree-browser" cellpadding="0" cellspacing="0">
        <tr class="js-header"><th>&nbsp;</th><th>name</th></tr>
        <tr class="js-no-results no-results" style="display: none">
          <th colspan="2">No matching files</th>
        </tr>
        <tbody class="js-results-list">
        </tbody>
      </table>
    </div>

    <div id="jump-to-line" style="display:none">
      <h2>Jump to Line</h2>
      <form>
        <input class="textfield" type="text">
        <div class="full-button">
          <button type="submit" class="classy">
            <span>Go</span>
          </button>
        </div>
      </form>
    </div>


        

      </div><!-- /.pagehead -->

      

  







<script type="text/javascript">
  GitHub.downloadRepo = '/richardscarrott/jquery-ui-carousel/archives/master'
  GitHub.revType = "master"

  GitHub.repoName = "jquery-ui-carousel"
  GitHub.controllerName = "blob"
  GitHub.actionName     = "show"
  GitHub.currentAction  = "blob#show"

  
    GitHub.loggedIn = true
    GitHub.hasWriteAccess = false
    GitHub.hasAdminAccess = false
    GitHub.watchingRepo = false
    GitHub.ignoredRepo = false
    GitHub.hasForkOfRepo = ""
    GitHub.hasForked = false
  

  
</script>







  <div id="commit">
    <div class="group">
        
  <div class="envelope commit">
    <div class="human">
      
        <div class="message"><pre><a href="/richardscarrott/jquery-ui-carousel/commit/d87446d14e1b87bb1d96c43445da7246c2f3d560">Fixed issue <a href="https://github.com/richardscarrott/jquery-ui-carousel/issues/4" title="Multiple .masks are added if nesting multiple ULs">#4</a></a> </pre></div>
      

      <div class="actor">
        <div class="gravatar">
          
          <img src="https://secure.gravatar.com/avatar/1e77d9c3de52e82db5177651d525a3cd?s=140&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" alt="" width="30" height="30"  />
        </div>
        <div class="name"><a href="/richardscarrott">richardscarrott</a> <span>(author)</span></div>
        <div class="date">
          <time class="js-relative-date" datetime="2011-06-19T08:23:54-07:00" title="2011-06-19 08:23:54">June 19, 2011</time>
        </div>
      </div>

      

    </div>
    <div class="machine">
      <span>c</span>ommit&nbsp;&nbsp;<a href="/richardscarrott/jquery-ui-carousel/commit/d87446d14e1b87bb1d96c43445da7246c2f3d560" class="js-commit-link" data-key="c">d87446d14e1b87bb1d96</a><br />
      <span>t</span>ree&nbsp;&nbsp;&nbsp;&nbsp;<a href="/richardscarrott/jquery-ui-carousel/tree/d87446d14e1b87bb1d96c43445da7246c2f3d560" class="js-tree-link" data-key="t">53bfcd3253140b7c0ea6</a><br />
      
        <span>p</span>arent&nbsp;
        
        <a href="/richardscarrott/jquery-ui-carousel/tree/ed1cdc69315c0a46470ed817afe080cf647b19cc" class="js-parent-link" data-key="p">ed1cdc69315c0a46470e</a>
      

    </div>
  </div>

    </div>
  </div>



  <div id="slider">

  

    <div class="breadcrumb" data-path="js/jquery.ui.carousel.js/">
      <b><a href="/richardscarrott/jquery-ui-carousel/tree/d87446d14e1b87bb1d96c43445da7246c2f3d560">jquery-ui-carousel</a></b> / <a href="/richardscarrott/jquery-ui-carousel/tree/d87446d14e1b87bb1d96c43445da7246c2f3d560/js">js</a> / jquery.ui.carousel.js       <span style="display:none" id="clippy_4757" class="clippy">js/jquery.ui.carousel.js</span>
      
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
              width="110"
              height="14"
              class="clippy"
              id="clippy" >
      <param name="movie" value="https://a248.e.akamai.net/assets.github.com/flash/clippy.swf?v5"/>
      <param name="allowScriptAccess" value="always" />
      <param name="quality" value="high" />
      <param name="scale" value="noscale" />
      <param NAME="FlashVars" value="id=clippy_4757&amp;copied=copied!&amp;copyto=copy to clipboard">
      <param name="bgcolor" value="#FFFFFF">
      <param name="wmode" value="opaque">
      <embed src="https://a248.e.akamai.net/assets.github.com/flash/clippy.swf?v5"
             width="110"
             height="14"
             name="clippy"
             quality="high"
             allowScriptAccess="always"
             type="application/x-shockwave-flash"
             pluginspage="http://www.macromedia.com/go/getflashplayer"
             FlashVars="id=clippy_4757&amp;copied=copied!&amp;copyto=copy to clipboard"
             bgcolor="#FFFFFF"
             wmode="opaque"
      />
      </object>
      

    </div>

    <div class="frames">
      <div class="frame frame-center" data-path="js/jquery.ui.carousel.js/" data-permalink-url="/richardscarrott/jquery-ui-carousel/blob/d87446d14e1b87bb1d96c43445da7246c2f3d560/js/jquery.ui.carousel.js">
        
          <ul class="big-actions">
            
            <li><a class="file-edit-link minibutton" href="/richardscarrott/jquery-ui-carousel/edit/__current_ref__/js/jquery.ui.carousel.js"><span>Edit this file</span></a></li>
          </ul>
        

        <div id="files">
          <div class="file">
            <div class="meta">
              <div class="info">
                <span class="icon"><img alt="Txt" height="16" src="https://a248.e.akamai.net/assets.github.com/images/icons/txt.png" width="16" /></span>
                <span class="mode" title="File Mode">100644</span>
                
                  <span>677 lines (479 sloc)</span>
                
                <span>14.634 kb</span>
              </div>
              <ul class="actions">
                <li><a href="/richardscarrott/jquery-ui-carousel/raw/master/js/jquery.ui.carousel.js" id="raw-url">raw</a></li>
                
                  <li><a href="/richardscarrott/jquery-ui-carousel/blame/master/js/jquery.ui.carousel.js">blame</a></li>
                
                <li><a href="/richardscarrott/jquery-ui-carousel/commits/master/js/jquery.ui.carousel.js">history</a></li>
              </ul>
            </div>
            
  <div class="data type-javascript">
    
      <table cellpadding="0" cellspacing="0" class="lines">
        <tr>
          <td>
            <pre class="line_numbers"><span id="L1" rel="#L1">1</span>
<span id="L2" rel="#L2">2</span>
<span id="L3" rel="#L3">3</span>
<span id="L4" rel="#L4">4</span>
<span id="L5" rel="#L5">5</span>
<span id="L6" rel="#L6">6</span>
<span id="L7" rel="#L7">7</span>
<span id="L8" rel="#L8">8</span>
<span id="L9" rel="#L9">9</span>
<span id="L10" rel="#L10">10</span>
<span id="L11" rel="#L11">11</span>
<span id="L12" rel="#L12">12</span>
<span id="L13" rel="#L13">13</span>
<span id="L14" rel="#L14">14</span>
<span id="L15" rel="#L15">15</span>
<span id="L16" rel="#L16">16</span>
<span id="L17" rel="#L17">17</span>
<span id="L18" rel="#L18">18</span>
<span id="L19" rel="#L19">19</span>
<span id="L20" rel="#L20">20</span>
<span id="L21" rel="#L21">21</span>
<span id="L22" rel="#L22">22</span>
<span id="L23" rel="#L23">23</span>
<span id="L24" rel="#L24">24</span>
<span id="L25" rel="#L25">25</span>
<span id="L26" rel="#L26">26</span>
<span id="L27" rel="#L27">27</span>
<span id="L28" rel="#L28">28</span>
<span id="L29" rel="#L29">29</span>
<span id="L30" rel="#L30">30</span>
<span id="L31" rel="#L31">31</span>
<span id="L32" rel="#L32">32</span>
<span id="L33" rel="#L33">33</span>
<span id="L34" rel="#L34">34</span>
<span id="L35" rel="#L35">35</span>
<span id="L36" rel="#L36">36</span>
<span id="L37" rel="#L37">37</span>
<span id="L38" rel="#L38">38</span>
<span id="L39" rel="#L39">39</span>
<span id="L40" rel="#L40">40</span>
<span id="L41" rel="#L41">41</span>
<span id="L42" rel="#L42">42</span>
<span id="L43" rel="#L43">43</span>
<span id="L44" rel="#L44">44</span>
<span id="L45" rel="#L45">45</span>
<span id="L46" rel="#L46">46</span>
<span id="L47" rel="#L47">47</span>
<span id="L48" rel="#L48">48</span>
<span id="L49" rel="#L49">49</span>
<span id="L50" rel="#L50">50</span>
<span id="L51" rel="#L51">51</span>
<span id="L52" rel="#L52">52</span>
<span id="L53" rel="#L53">53</span>
<span id="L54" rel="#L54">54</span>
<span id="L55" rel="#L55">55</span>
<span id="L56" rel="#L56">56</span>
<span id="L57" rel="#L57">57</span>
<span id="L58" rel="#L58">58</span>
<span id="L59" rel="#L59">59</span>
<span id="L60" rel="#L60">60</span>
<span id="L61" rel="#L61">61</span>
<span id="L62" rel="#L62">62</span>
<span id="L63" rel="#L63">63</span>
<span id="L64" rel="#L64">64</span>
<span id="L65" rel="#L65">65</span>
<span id="L66" rel="#L66">66</span>
<span id="L67" rel="#L67">67</span>
<span id="L68" rel="#L68">68</span>
<span id="L69" rel="#L69">69</span>
<span id="L70" rel="#L70">70</span>
<span id="L71" rel="#L71">71</span>
<span id="L72" rel="#L72">72</span>
<span id="L73" rel="#L73">73</span>
<span id="L74" rel="#L74">74</span>
<span id="L75" rel="#L75">75</span>
<span id="L76" rel="#L76">76</span>
<span id="L77" rel="#L77">77</span>
<span id="L78" rel="#L78">78</span>
<span id="L79" rel="#L79">79</span>
<span id="L80" rel="#L80">80</span>
<span id="L81" rel="#L81">81</span>
<span id="L82" rel="#L82">82</span>
<span id="L83" rel="#L83">83</span>
<span id="L84" rel="#L84">84</span>
<span id="L85" rel="#L85">85</span>
<span id="L86" rel="#L86">86</span>
<span id="L87" rel="#L87">87</span>
<span id="L88" rel="#L88">88</span>
<span id="L89" rel="#L89">89</span>
<span id="L90" rel="#L90">90</span>
<span id="L91" rel="#L91">91</span>
<span id="L92" rel="#L92">92</span>
<span id="L93" rel="#L93">93</span>
<span id="L94" rel="#L94">94</span>
<span id="L95" rel="#L95">95</span>
<span id="L96" rel="#L96">96</span>
<span id="L97" rel="#L97">97</span>
<span id="L98" rel="#L98">98</span>
<span id="L99" rel="#L99">99</span>
<span id="L100" rel="#L100">100</span>
<span id="L101" rel="#L101">101</span>
<span id="L102" rel="#L102">102</span>
<span id="L103" rel="#L103">103</span>
<span id="L104" rel="#L104">104</span>
<span id="L105" rel="#L105">105</span>
<span id="L106" rel="#L106">106</span>
<span id="L107" rel="#L107">107</span>
<span id="L108" rel="#L108">108</span>
<span id="L109" rel="#L109">109</span>
<span id="L110" rel="#L110">110</span>
<span id="L111" rel="#L111">111</span>
<span id="L112" rel="#L112">112</span>
<span id="L113" rel="#L113">113</span>
<span id="L114" rel="#L114">114</span>
<span id="L115" rel="#L115">115</span>
<span id="L116" rel="#L116">116</span>
<span id="L117" rel="#L117">117</span>
<span id="L118" rel="#L118">118</span>
<span id="L119" rel="#L119">119</span>
<span id="L120" rel="#L120">120</span>
<span id="L121" rel="#L121">121</span>
<span id="L122" rel="#L122">122</span>
<span id="L123" rel="#L123">123</span>
<span id="L124" rel="#L124">124</span>
<span id="L125" rel="#L125">125</span>
<span id="L126" rel="#L126">126</span>
<span id="L127" rel="#L127">127</span>
<span id="L128" rel="#L128">128</span>
<span id="L129" rel="#L129">129</span>
<span id="L130" rel="#L130">130</span>
<span id="L131" rel="#L131">131</span>
<span id="L132" rel="#L132">132</span>
<span id="L133" rel="#L133">133</span>
<span id="L134" rel="#L134">134</span>
<span id="L135" rel="#L135">135</span>
<span id="L136" rel="#L136">136</span>
<span id="L137" rel="#L137">137</span>
<span id="L138" rel="#L138">138</span>
<span id="L139" rel="#L139">139</span>
<span id="L140" rel="#L140">140</span>
<span id="L141" rel="#L141">141</span>
<span id="L142" rel="#L142">142</span>
<span id="L143" rel="#L143">143</span>
<span id="L144" rel="#L144">144</span>
<span id="L145" rel="#L145">145</span>
<span id="L146" rel="#L146">146</span>
<span id="L147" rel="#L147">147</span>
<span id="L148" rel="#L148">148</span>
<span id="L149" rel="#L149">149</span>
<span id="L150" rel="#L150">150</span>
<span id="L151" rel="#L151">151</span>
<span id="L152" rel="#L152">152</span>
<span id="L153" rel="#L153">153</span>
<span id="L154" rel="#L154">154</span>
<span id="L155" rel="#L155">155</span>
<span id="L156" rel="#L156">156</span>
<span id="L157" rel="#L157">157</span>
<span id="L158" rel="#L158">158</span>
<span id="L159" rel="#L159">159</span>
<span id="L160" rel="#L160">160</span>
<span id="L161" rel="#L161">161</span>
<span id="L162" rel="#L162">162</span>
<span id="L163" rel="#L163">163</span>
<span id="L164" rel="#L164">164</span>
<span id="L165" rel="#L165">165</span>
<span id="L166" rel="#L166">166</span>
<span id="L167" rel="#L167">167</span>
<span id="L168" rel="#L168">168</span>
<span id="L169" rel="#L169">169</span>
<span id="L170" rel="#L170">170</span>
<span id="L171" rel="#L171">171</span>
<span id="L172" rel="#L172">172</span>
<span id="L173" rel="#L173">173</span>
<span id="L174" rel="#L174">174</span>
<span id="L175" rel="#L175">175</span>
<span id="L176" rel="#L176">176</span>
<span id="L177" rel="#L177">177</span>
<span id="L178" rel="#L178">178</span>
<span id="L179" rel="#L179">179</span>
<span id="L180" rel="#L180">180</span>
<span id="L181" rel="#L181">181</span>
<span id="L182" rel="#L182">182</span>
<span id="L183" rel="#L183">183</span>
<span id="L184" rel="#L184">184</span>
<span id="L185" rel="#L185">185</span>
<span id="L186" rel="#L186">186</span>
<span id="L187" rel="#L187">187</span>
<span id="L188" rel="#L188">188</span>
<span id="L189" rel="#L189">189</span>
<span id="L190" rel="#L190">190</span>
<span id="L191" rel="#L191">191</span>
<span id="L192" rel="#L192">192</span>
<span id="L193" rel="#L193">193</span>
<span id="L194" rel="#L194">194</span>
<span id="L195" rel="#L195">195</span>
<span id="L196" rel="#L196">196</span>
<span id="L197" rel="#L197">197</span>
<span id="L198" rel="#L198">198</span>
<span id="L199" rel="#L199">199</span>
<span id="L200" rel="#L200">200</span>
<span id="L201" rel="#L201">201</span>
<span id="L202" rel="#L202">202</span>
<span id="L203" rel="#L203">203</span>
<span id="L204" rel="#L204">204</span>
<span id="L205" rel="#L205">205</span>
<span id="L206" rel="#L206">206</span>
<span id="L207" rel="#L207">207</span>
<span id="L208" rel="#L208">208</span>
<span id="L209" rel="#L209">209</span>
<span id="L210" rel="#L210">210</span>
<span id="L211" rel="#L211">211</span>
<span id="L212" rel="#L212">212</span>
<span id="L213" rel="#L213">213</span>
<span id="L214" rel="#L214">214</span>
<span id="L215" rel="#L215">215</span>
<span id="L216" rel="#L216">216</span>
<span id="L217" rel="#L217">217</span>
<span id="L218" rel="#L218">218</span>
<span id="L219" rel="#L219">219</span>
<span id="L220" rel="#L220">220</span>
<span id="L221" rel="#L221">221</span>
<span id="L222" rel="#L222">222</span>
<span id="L223" rel="#L223">223</span>
<span id="L224" rel="#L224">224</span>
<span id="L225" rel="#L225">225</span>
<span id="L226" rel="#L226">226</span>
<span id="L227" rel="#L227">227</span>
<span id="L228" rel="#L228">228</span>
<span id="L229" rel="#L229">229</span>
<span id="L230" rel="#L230">230</span>
<span id="L231" rel="#L231">231</span>
<span id="L232" rel="#L232">232</span>
<span id="L233" rel="#L233">233</span>
<span id="L234" rel="#L234">234</span>
<span id="L235" rel="#L235">235</span>
<span id="L236" rel="#L236">236</span>
<span id="L237" rel="#L237">237</span>
<span id="L238" rel="#L238">238</span>
<span id="L239" rel="#L239">239</span>
<span id="L240" rel="#L240">240</span>
<span id="L241" rel="#L241">241</span>
<span id="L242" rel="#L242">242</span>
<span id="L243" rel="#L243">243</span>
<span id="L244" rel="#L244">244</span>
<span id="L245" rel="#L245">245</span>
<span id="L246" rel="#L246">246</span>
<span id="L247" rel="#L247">247</span>
<span id="L248" rel="#L248">248</span>
<span id="L249" rel="#L249">249</span>
<span id="L250" rel="#L250">250</span>
<span id="L251" rel="#L251">251</span>
<span id="L252" rel="#L252">252</span>
<span id="L253" rel="#L253">253</span>
<span id="L254" rel="#L254">254</span>
<span id="L255" rel="#L255">255</span>
<span id="L256" rel="#L256">256</span>
<span id="L257" rel="#L257">257</span>
<span id="L258" rel="#L258">258</span>
<span id="L259" rel="#L259">259</span>
<span id="L260" rel="#L260">260</span>
<span id="L261" rel="#L261">261</span>
<span id="L262" rel="#L262">262</span>
<span id="L263" rel="#L263">263</span>
<span id="L264" rel="#L264">264</span>
<span id="L265" rel="#L265">265</span>
<span id="L266" rel="#L266">266</span>
<span id="L267" rel="#L267">267</span>
<span id="L268" rel="#L268">268</span>
<span id="L269" rel="#L269">269</span>
<span id="L270" rel="#L270">270</span>
<span id="L271" rel="#L271">271</span>
<span id="L272" rel="#L272">272</span>
<span id="L273" rel="#L273">273</span>
<span id="L274" rel="#L274">274</span>
<span id="L275" rel="#L275">275</span>
<span id="L276" rel="#L276">276</span>
<span id="L277" rel="#L277">277</span>
<span id="L278" rel="#L278">278</span>
<span id="L279" rel="#L279">279</span>
<span id="L280" rel="#L280">280</span>
<span id="L281" rel="#L281">281</span>
<span id="L282" rel="#L282">282</span>
<span id="L283" rel="#L283">283</span>
<span id="L284" rel="#L284">284</span>
<span id="L285" rel="#L285">285</span>
<span id="L286" rel="#L286">286</span>
<span id="L287" rel="#L287">287</span>
<span id="L288" rel="#L288">288</span>
<span id="L289" rel="#L289">289</span>
<span id="L290" rel="#L290">290</span>
<span id="L291" rel="#L291">291</span>
<span id="L292" rel="#L292">292</span>
<span id="L293" rel="#L293">293</span>
<span id="L294" rel="#L294">294</span>
<span id="L295" rel="#L295">295</span>
<span id="L296" rel="#L296">296</span>
<span id="L297" rel="#L297">297</span>
<span id="L298" rel="#L298">298</span>
<span id="L299" rel="#L299">299</span>
<span id="L300" rel="#L300">300</span>
<span id="L301" rel="#L301">301</span>
<span id="L302" rel="#L302">302</span>
<span id="L303" rel="#L303">303</span>
<span id="L304" rel="#L304">304</span>
<span id="L305" rel="#L305">305</span>
<span id="L306" rel="#L306">306</span>
<span id="L307" rel="#L307">307</span>
<span id="L308" rel="#L308">308</span>
<span id="L309" rel="#L309">309</span>
<span id="L310" rel="#L310">310</span>
<span id="L311" rel="#L311">311</span>
<span id="L312" rel="#L312">312</span>
<span id="L313" rel="#L313">313</span>
<span id="L314" rel="#L314">314</span>
<span id="L315" rel="#L315">315</span>
<span id="L316" rel="#L316">316</span>
<span id="L317" rel="#L317">317</span>
<span id="L318" rel="#L318">318</span>
<span id="L319" rel="#L319">319</span>
<span id="L320" rel="#L320">320</span>
<span id="L321" rel="#L321">321</span>
<span id="L322" rel="#L322">322</span>
<span id="L323" rel="#L323">323</span>
<span id="L324" rel="#L324">324</span>
<span id="L325" rel="#L325">325</span>
<span id="L326" rel="#L326">326</span>
<span id="L327" rel="#L327">327</span>
<span id="L328" rel="#L328">328</span>
<span id="L329" rel="#L329">329</span>
<span id="L330" rel="#L330">330</span>
<span id="L331" rel="#L331">331</span>
<span id="L332" rel="#L332">332</span>
<span id="L333" rel="#L333">333</span>
<span id="L334" rel="#L334">334</span>
<span id="L335" rel="#L335">335</span>
<span id="L336" rel="#L336">336</span>
<span id="L337" rel="#L337">337</span>
<span id="L338" rel="#L338">338</span>
<span id="L339" rel="#L339">339</span>
<span id="L340" rel="#L340">340</span>
<span id="L341" rel="#L341">341</span>
<span id="L342" rel="#L342">342</span>
<span id="L343" rel="#L343">343</span>
<span id="L344" rel="#L344">344</span>
<span id="L345" rel="#L345">345</span>
<span id="L346" rel="#L346">346</span>
<span id="L347" rel="#L347">347</span>
<span id="L348" rel="#L348">348</span>
<span id="L349" rel="#L349">349</span>
<span id="L350" rel="#L350">350</span>
<span id="L351" rel="#L351">351</span>
<span id="L352" rel="#L352">352</span>
<span id="L353" rel="#L353">353</span>
<span id="L354" rel="#L354">354</span>
<span id="L355" rel="#L355">355</span>
<span id="L356" rel="#L356">356</span>
<span id="L357" rel="#L357">357</span>
<span id="L358" rel="#L358">358</span>
<span id="L359" rel="#L359">359</span>
<span id="L360" rel="#L360">360</span>
<span id="L361" rel="#L361">361</span>
<span id="L362" rel="#L362">362</span>
<span id="L363" rel="#L363">363</span>
<span id="L364" rel="#L364">364</span>
<span id="L365" rel="#L365">365</span>
<span id="L366" rel="#L366">366</span>
<span id="L367" rel="#L367">367</span>
<span id="L368" rel="#L368">368</span>
<span id="L369" rel="#L369">369</span>
<span id="L370" rel="#L370">370</span>
<span id="L371" rel="#L371">371</span>
<span id="L372" rel="#L372">372</span>
<span id="L373" rel="#L373">373</span>
<span id="L374" rel="#L374">374</span>
<span id="L375" rel="#L375">375</span>
<span id="L376" rel="#L376">376</span>
<span id="L377" rel="#L377">377</span>
<span id="L378" rel="#L378">378</span>
<span id="L379" rel="#L379">379</span>
<span id="L380" rel="#L380">380</span>
<span id="L381" rel="#L381">381</span>
<span id="L382" rel="#L382">382</span>
<span id="L383" rel="#L383">383</span>
<span id="L384" rel="#L384">384</span>
<span id="L385" rel="#L385">385</span>
<span id="L386" rel="#L386">386</span>
<span id="L387" rel="#L387">387</span>
<span id="L388" rel="#L388">388</span>
<span id="L389" rel="#L389">389</span>
<span id="L390" rel="#L390">390</span>
<span id="L391" rel="#L391">391</span>
<span id="L392" rel="#L392">392</span>
<span id="L393" rel="#L393">393</span>
<span id="L394" rel="#L394">394</span>
<span id="L395" rel="#L395">395</span>
<span id="L396" rel="#L396">396</span>
<span id="L397" rel="#L397">397</span>
<span id="L398" rel="#L398">398</span>
<span id="L399" rel="#L399">399</span>
<span id="L400" rel="#L400">400</span>
<span id="L401" rel="#L401">401</span>
<span id="L402" rel="#L402">402</span>
<span id="L403" rel="#L403">403</span>
<span id="L404" rel="#L404">404</span>
<span id="L405" rel="#L405">405</span>
<span id="L406" rel="#L406">406</span>
<span id="L407" rel="#L407">407</span>
<span id="L408" rel="#L408">408</span>
<span id="L409" rel="#L409">409</span>
<span id="L410" rel="#L410">410</span>
<span id="L411" rel="#L411">411</span>
<span id="L412" rel="#L412">412</span>
<span id="L413" rel="#L413">413</span>
<span id="L414" rel="#L414">414</span>
<span id="L415" rel="#L415">415</span>
<span id="L416" rel="#L416">416</span>
<span id="L417" rel="#L417">417</span>
<span id="L418" rel="#L418">418</span>
<span id="L419" rel="#L419">419</span>
<span id="L420" rel="#L420">420</span>
<span id="L421" rel="#L421">421</span>
<span id="L422" rel="#L422">422</span>
<span id="L423" rel="#L423">423</span>
<span id="L424" rel="#L424">424</span>
<span id="L425" rel="#L425">425</span>
<span id="L426" rel="#L426">426</span>
<span id="L427" rel="#L427">427</span>
<span id="L428" rel="#L428">428</span>
<span id="L429" rel="#L429">429</span>
<span id="L430" rel="#L430">430</span>
<span id="L431" rel="#L431">431</span>
<span id="L432" rel="#L432">432</span>
<span id="L433" rel="#L433">433</span>
<span id="L434" rel="#L434">434</span>
<span id="L435" rel="#L435">435</span>
<span id="L436" rel="#L436">436</span>
<span id="L437" rel="#L437">437</span>
<span id="L438" rel="#L438">438</span>
<span id="L439" rel="#L439">439</span>
<span id="L440" rel="#L440">440</span>
<span id="L441" rel="#L441">441</span>
<span id="L442" rel="#L442">442</span>
<span id="L443" rel="#L443">443</span>
<span id="L444" rel="#L444">444</span>
<span id="L445" rel="#L445">445</span>
<span id="L446" rel="#L446">446</span>
<span id="L447" rel="#L447">447</span>
<span id="L448" rel="#L448">448</span>
<span id="L449" rel="#L449">449</span>
<span id="L450" rel="#L450">450</span>
<span id="L451" rel="#L451">451</span>
<span id="L452" rel="#L452">452</span>
<span id="L453" rel="#L453">453</span>
<span id="L454" rel="#L454">454</span>
<span id="L455" rel="#L455">455</span>
<span id="L456" rel="#L456">456</span>
<span id="L457" rel="#L457">457</span>
<span id="L458" rel="#L458">458</span>
<span id="L459" rel="#L459">459</span>
<span id="L460" rel="#L460">460</span>
<span id="L461" rel="#L461">461</span>
<span id="L462" rel="#L462">462</span>
<span id="L463" rel="#L463">463</span>
<span id="L464" rel="#L464">464</span>
<span id="L465" rel="#L465">465</span>
<span id="L466" rel="#L466">466</span>
<span id="L467" rel="#L467">467</span>
<span id="L468" rel="#L468">468</span>
<span id="L469" rel="#L469">469</span>
<span id="L470" rel="#L470">470</span>
<span id="L471" rel="#L471">471</span>
<span id="L472" rel="#L472">472</span>
<span id="L473" rel="#L473">473</span>
<span id="L474" rel="#L474">474</span>
<span id="L475" rel="#L475">475</span>
<span id="L476" rel="#L476">476</span>
<span id="L477" rel="#L477">477</span>
<span id="L478" rel="#L478">478</span>
<span id="L479" rel="#L479">479</span>
<span id="L480" rel="#L480">480</span>
<span id="L481" rel="#L481">481</span>
<span id="L482" rel="#L482">482</span>
<span id="L483" rel="#L483">483</span>
<span id="L484" rel="#L484">484</span>
<span id="L485" rel="#L485">485</span>
<span id="L486" rel="#L486">486</span>
<span id="L487" rel="#L487">487</span>
<span id="L488" rel="#L488">488</span>
<span id="L489" rel="#L489">489</span>
<span id="L490" rel="#L490">490</span>
<span id="L491" rel="#L491">491</span>
<span id="L492" rel="#L492">492</span>
<span id="L493" rel="#L493">493</span>
<span id="L494" rel="#L494">494</span>
<span id="L495" rel="#L495">495</span>
<span id="L496" rel="#L496">496</span>
<span id="L497" rel="#L497">497</span>
<span id="L498" rel="#L498">498</span>
<span id="L499" rel="#L499">499</span>
<span id="L500" rel="#L500">500</span>
<span id="L501" rel="#L501">501</span>
<span id="L502" rel="#L502">502</span>
<span id="L503" rel="#L503">503</span>
<span id="L504" rel="#L504">504</span>
<span id="L505" rel="#L505">505</span>
<span id="L506" rel="#L506">506</span>
<span id="L507" rel="#L507">507</span>
<span id="L508" rel="#L508">508</span>
<span id="L509" rel="#L509">509</span>
<span id="L510" rel="#L510">510</span>
<span id="L511" rel="#L511">511</span>
<span id="L512" rel="#L512">512</span>
<span id="L513" rel="#L513">513</span>
<span id="L514" rel="#L514">514</span>
<span id="L515" rel="#L515">515</span>
<span id="L516" rel="#L516">516</span>
<span id="L517" rel="#L517">517</span>
<span id="L518" rel="#L518">518</span>
<span id="L519" rel="#L519">519</span>
<span id="L520" rel="#L520">520</span>
<span id="L521" rel="#L521">521</span>
<span id="L522" rel="#L522">522</span>
<span id="L523" rel="#L523">523</span>
<span id="L524" rel="#L524">524</span>
<span id="L525" rel="#L525">525</span>
<span id="L526" rel="#L526">526</span>
<span id="L527" rel="#L527">527</span>
<span id="L528" rel="#L528">528</span>
<span id="L529" rel="#L529">529</span>
<span id="L530" rel="#L530">530</span>
<span id="L531" rel="#L531">531</span>
<span id="L532" rel="#L532">532</span>
<span id="L533" rel="#L533">533</span>
<span id="L534" rel="#L534">534</span>
<span id="L535" rel="#L535">535</span>
<span id="L536" rel="#L536">536</span>
<span id="L537" rel="#L537">537</span>
<span id="L538" rel="#L538">538</span>
<span id="L539" rel="#L539">539</span>
<span id="L540" rel="#L540">540</span>
<span id="L541" rel="#L541">541</span>
<span id="L542" rel="#L542">542</span>
<span id="L543" rel="#L543">543</span>
<span id="L544" rel="#L544">544</span>
<span id="L545" rel="#L545">545</span>
<span id="L546" rel="#L546">546</span>
<span id="L547" rel="#L547">547</span>
<span id="L548" rel="#L548">548</span>
<span id="L549" rel="#L549">549</span>
<span id="L550" rel="#L550">550</span>
<span id="L551" rel="#L551">551</span>
<span id="L552" rel="#L552">552</span>
<span id="L553" rel="#L553">553</span>
<span id="L554" rel="#L554">554</span>
<span id="L555" rel="#L555">555</span>
<span id="L556" rel="#L556">556</span>
<span id="L557" rel="#L557">557</span>
<span id="L558" rel="#L558">558</span>
<span id="L559" rel="#L559">559</span>
<span id="L560" rel="#L560">560</span>
<span id="L561" rel="#L561">561</span>
<span id="L562" rel="#L562">562</span>
<span id="L563" rel="#L563">563</span>
<span id="L564" rel="#L564">564</span>
<span id="L565" rel="#L565">565</span>
<span id="L566" rel="#L566">566</span>
<span id="L567" rel="#L567">567</span>
<span id="L568" rel="#L568">568</span>
<span id="L569" rel="#L569">569</span>
<span id="L570" rel="#L570">570</span>
<span id="L571" rel="#L571">571</span>
<span id="L572" rel="#L572">572</span>
<span id="L573" rel="#L573">573</span>
<span id="L574" rel="#L574">574</span>
<span id="L575" rel="#L575">575</span>
<span id="L576" rel="#L576">576</span>
<span id="L577" rel="#L577">577</span>
<span id="L578" rel="#L578">578</span>
<span id="L579" rel="#L579">579</span>
<span id="L580" rel="#L580">580</span>
<span id="L581" rel="#L581">581</span>
<span id="L582" rel="#L582">582</span>
<span id="L583" rel="#L583">583</span>
<span id="L584" rel="#L584">584</span>
<span id="L585" rel="#L585">585</span>
<span id="L586" rel="#L586">586</span>
<span id="L587" rel="#L587">587</span>
<span id="L588" rel="#L588">588</span>
<span id="L589" rel="#L589">589</span>
<span id="L590" rel="#L590">590</span>
<span id="L591" rel="#L591">591</span>
<span id="L592" rel="#L592">592</span>
<span id="L593" rel="#L593">593</span>
<span id="L594" rel="#L594">594</span>
<span id="L595" rel="#L595">595</span>
<span id="L596" rel="#L596">596</span>
<span id="L597" rel="#L597">597</span>
<span id="L598" rel="#L598">598</span>
<span id="L599" rel="#L599">599</span>
<span id="L600" rel="#L600">600</span>
<span id="L601" rel="#L601">601</span>
<span id="L602" rel="#L602">602</span>
<span id="L603" rel="#L603">603</span>
<span id="L604" rel="#L604">604</span>
<span id="L605" rel="#L605">605</span>
<span id="L606" rel="#L606">606</span>
<span id="L607" rel="#L607">607</span>
<span id="L608" rel="#L608">608</span>
<span id="L609" rel="#L609">609</span>
<span id="L610" rel="#L610">610</span>
<span id="L611" rel="#L611">611</span>
<span id="L612" rel="#L612">612</span>
<span id="L613" rel="#L613">613</span>
<span id="L614" rel="#L614">614</span>
<span id="L615" rel="#L615">615</span>
<span id="L616" rel="#L616">616</span>
<span id="L617" rel="#L617">617</span>
<span id="L618" rel="#L618">618</span>
<span id="L619" rel="#L619">619</span>
<span id="L620" rel="#L620">620</span>
<span id="L621" rel="#L621">621</span>
<span id="L622" rel="#L622">622</span>
<span id="L623" rel="#L623">623</span>
<span id="L624" rel="#L624">624</span>
<span id="L625" rel="#L625">625</span>
<span id="L626" rel="#L626">626</span>
<span id="L627" rel="#L627">627</span>
<span id="L628" rel="#L628">628</span>
<span id="L629" rel="#L629">629</span>
<span id="L630" rel="#L630">630</span>
<span id="L631" rel="#L631">631</span>
<span id="L632" rel="#L632">632</span>
<span id="L633" rel="#L633">633</span>
<span id="L634" rel="#L634">634</span>
<span id="L635" rel="#L635">635</span>
<span id="L636" rel="#L636">636</span>
<span id="L637" rel="#L637">637</span>
<span id="L638" rel="#L638">638</span>
<span id="L639" rel="#L639">639</span>
<span id="L640" rel="#L640">640</span>
<span id="L641" rel="#L641">641</span>
<span id="L642" rel="#L642">642</span>
<span id="L643" rel="#L643">643</span>
<span id="L644" rel="#L644">644</span>
<span id="L645" rel="#L645">645</span>
<span id="L646" rel="#L646">646</span>
<span id="L647" rel="#L647">647</span>
<span id="L648" rel="#L648">648</span>
<span id="L649" rel="#L649">649</span>
<span id="L650" rel="#L650">650</span>
<span id="L651" rel="#L651">651</span>
<span id="L652" rel="#L652">652</span>
<span id="L653" rel="#L653">653</span>
<span id="L654" rel="#L654">654</span>
<span id="L655" rel="#L655">655</span>
<span id="L656" rel="#L656">656</span>
<span id="L657" rel="#L657">657</span>
<span id="L658" rel="#L658">658</span>
<span id="L659" rel="#L659">659</span>
<span id="L660" rel="#L660">660</span>
<span id="L661" rel="#L661">661</span>
<span id="L662" rel="#L662">662</span>
<span id="L663" rel="#L663">663</span>
<span id="L664" rel="#L664">664</span>
<span id="L665" rel="#L665">665</span>
<span id="L666" rel="#L666">666</span>
<span id="L667" rel="#L667">667</span>
<span id="L668" rel="#L668">668</span>
<span id="L669" rel="#L669">669</span>
<span id="L670" rel="#L670">670</span>
<span id="L671" rel="#L671">671</span>
<span id="L672" rel="#L672">672</span>
<span id="L673" rel="#L673">673</span>
<span id="L674" rel="#L674">674</span>
<span id="L675" rel="#L675">675</span>
<span id="L676" rel="#L676">676</span>
<span id="L677" rel="#L677">677</span>
</pre>
          </td>
          <td width="100%">
            
              
                <div class="highlight"><pre><div class='line' id='LC1'><span class="cm">/*</span></div><div class='line' id='LC2'><span class="cm"> * jQuery UI Carousel Plugin v0.7.5</span></div><div class='line' id='LC3'><span class="cm"> *</span></div><div class='line' id='LC4'><span class="cm"> * Copyright (c) 2011 Richard Scarrott</span></div><div class='line' id='LC5'><span class="cm"> * http://www.richardscarrott.co.uk</span></div><div class='line' id='LC6'><span class="cm"> *</span></div><div class='line' id='LC7'><span class="cm"> * Dual licensed under the MIT and GPL licenses:</span></div><div class='line' id='LC8'><span class="cm"> * http://www.opensource.org/licenses/mit-license.php</span></div><div class='line' id='LC9'><span class="cm"> * http://www.gnu.org/licenses/gpl.html</span></div><div class='line' id='LC10'><span class="cm"> *</span></div><div class='line' id='LC11'><span class="cm"> * Depends:</span></div><div class='line' id='LC12'><span class="cm"> *  jquery.js v1.4+</span></div><div class='line' id='LC13'><span class="cm"> *  jquery.ui.widget.js v1.8+</span></div><div class='line' id='LC14'><span class="cm"> *</span></div><div class='line' id='LC15'><span class="cm"> */</span></div><div class='line' id='LC16'>&nbsp;</div><div class='line' id='LC17'><span class="p">(</span><span class="kd">function</span> <span class="p">(</span><span class="nx">$</span><span class="p">,</span> <span class="kc">undefined</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC18'><br/></div><div class='line' id='LC19'>	<span class="kd">var</span> <span class="nx">_super</span> <span class="o">=</span> <span class="nx">$</span><span class="p">.</span><span class="nx">Widget</span><span class="p">.</span><span class="nx">prototype</span><span class="p">,</span></div><div class='line' id='LC20'>		<span class="nx">horizontal</span> <span class="o">=</span> <span class="p">{</span></div><div class='line' id='LC21'>			<span class="nx">pos</span><span class="o">:</span> <span class="s1">&#39;left&#39;</span><span class="p">,</span></div><div class='line' id='LC22'>			<span class="nx">pos2</span><span class="o">:</span> <span class="s1">&#39;right&#39;</span><span class="p">,</span></div><div class='line' id='LC23'>			<span class="nx">dim</span><span class="o">:</span> <span class="s1">&#39;width&#39;</span></div><div class='line' id='LC24'>		<span class="p">},</span></div><div class='line' id='LC25'>		<span class="nx">vertical</span> <span class="o">=</span> <span class="p">{</span></div><div class='line' id='LC26'>			<span class="nx">pos</span><span class="o">:</span> <span class="s1">&#39;top&#39;</span><span class="p">,</span></div><div class='line' id='LC27'>			<span class="nx">pos2</span><span class="o">:</span> <span class="s1">&#39;bottom&#39;</span><span class="p">,</span></div><div class='line' id='LC28'>			<span class="nx">dim</span><span class="o">:</span> <span class="s1">&#39;height&#39;</span></div><div class='line' id='LC29'>		<span class="p">};</span></div><div class='line' id='LC30'><br/></div><div class='line' id='LC31'>	<span class="nx">$</span><span class="p">.</span><span class="nx">widget</span><span class="p">(</span><span class="s1">&#39;ui.carousel&#39;</span><span class="p">,</span> <span class="p">{</span></div><div class='line' id='LC32'><br/></div><div class='line' id='LC33'>		<span class="c1">// holds original class string</span></div><div class='line' id='LC34'>		<span class="nx">oldClass</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC35'><br/></div><div class='line' id='LC36'>		<span class="nx">options</span><span class="o">:</span> <span class="p">{</span></div><div class='line' id='LC37'>			<span class="nx">itemsPerPage</span><span class="o">:</span> <span class="s1">&#39;auto&#39;</span><span class="p">,</span></div><div class='line' id='LC38'>			<span class="nx">itemsPerTransition</span><span class="o">:</span> <span class="s1">&#39;auto&#39;</span><span class="p">,</span></div><div class='line' id='LC39'>			<span class="nx">orientation</span><span class="o">:</span> <span class="s1">&#39;horizontal&#39;</span><span class="p">,</span></div><div class='line' id='LC40'>			<span class="nx">noOfRows</span><span class="o">:</span> <span class="mi">1</span><span class="p">,</span> <span class="c1">// horizontal only</span></div><div class='line' id='LC41'>			<span class="nx">pagination</span><span class="o">:</span> <span class="kc">true</span><span class="p">,</span></div><div class='line' id='LC42'>			<span class="nx">insertPagination</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC43'>			<span class="nx">nextPrevActions</span><span class="o">:</span> <span class="kc">true</span><span class="p">,</span></div><div class='line' id='LC44'>			<span class="nx">insertNextAction</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC45'>			<span class="nx">insertPrevAction</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC46'>			<span class="nx">speed</span><span class="o">:</span> <span class="s1">&#39;normal&#39;</span><span class="p">,</span></div><div class='line' id='LC47'>			<span class="nx">easing</span><span class="o">:</span> <span class="s1">&#39;swing&#39;</span><span class="p">,</span></div><div class='line' id='LC48'>			<span class="nx">startAt</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC49'><br/></div><div class='line' id='LC50'>			<span class="c1">// callbacks</span></div><div class='line' id='LC51'>			<span class="nx">init</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC52'>			<span class="nx">beforeAnimate</span><span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC53'>			<span class="nx">afterAnimate</span><span class="o">:</span> <span class="kc">null</span></div><div class='line' id='LC54'>		<span class="p">},</span></div><div class='line' id='LC55'><br/></div><div class='line' id='LC56'>		<span class="nx">_create</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC57'><br/></div><div class='line' id='LC58'>			<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">=</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC59'><br/></div><div class='line' id='LC60'>			<span class="k">this</span><span class="p">.</span><span class="nx">_elements</span><span class="p">();</span></div><div class='line' id='LC61'>			<span class="k">this</span><span class="p">.</span><span class="nx">_addClasses</span><span class="p">();</span></div><div class='line' id='LC62'>			<span class="k">this</span><span class="p">.</span><span class="nx">_defineOrientation</span><span class="p">();</span></div><div class='line' id='LC63'>			<span class="k">this</span><span class="p">.</span><span class="nx">_addMask</span><span class="p">();</span></div><div class='line' id='LC64'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setMaskDim</span><span class="p">();</span></div><div class='line' id='LC65'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setItemDim</span><span class="p">();</span></div><div class='line' id='LC66'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setNoOfItems</span><span class="p">();</span></div><div class='line' id='LC67'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setNoOfPages</span><span class="p">();</span></div><div class='line' id='LC68'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setRunnerWidth</span><span class="p">();</span></div><div class='line' id='LC69'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setLastPos</span><span class="p">();</span></div><div class='line' id='LC70'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setLastItem</span><span class="p">();</span></div><div class='line' id='LC71'>			<span class="k">this</span><span class="p">.</span><span class="nx">_addPagination</span><span class="p">();</span></div><div class='line' id='LC72'>			<span class="k">this</span><span class="p">.</span><span class="nx">_addNextPrevActions</span><span class="p">();</span></div><div class='line' id='LC73'><br/></div><div class='line' id='LC74'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">startAt</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC75'>				<span class="k">this</span><span class="p">.</span><span class="nx">goToItem</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">startAt</span><span class="p">,</span> <span class="kc">false</span><span class="p">);</span></div><div class='line' id='LC76'>			<span class="p">}</span></div><div class='line' id='LC77'><br/></div><div class='line' id='LC78'>			<span class="k">this</span><span class="p">.</span><span class="nx">_updateUi</span><span class="p">();</span></div><div class='line' id='LC79'><br/></div><div class='line' id='LC80'>			<span class="k">this</span><span class="p">.</span><span class="nx">_trigger</span><span class="p">(</span><span class="s1">&#39;init&#39;</span><span class="p">,</span> <span class="kc">null</span><span class="p">,</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getData</span><span class="p">());</span></div><div class='line' id='LC81'><br/></div><div class='line' id='LC82'>		<span class="p">},</span></div><div class='line' id='LC83'><br/></div><div class='line' id='LC84'>		<span class="c1">// caches DOM elements</span></div><div class='line' id='LC85'>		<span class="nx">_elements</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC86'><br/></div><div class='line' id='LC87'>			<span class="kd">var</span> <span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span> <span class="o">=</span> <span class="p">{};</span></div><div class='line' id='LC88'><br/></div><div class='line' id='LC89'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">mask</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">.</span><span class="nx">children</span><span class="p">(</span><span class="s1">&#39;.mask&#39;</span><span class="p">);</span></div><div class='line' id='LC90'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">.</span><span class="nx">find</span><span class="p">(</span><span class="s1">&#39;ul&#39;</span><span class="p">).</span><span class="nx">first</span><span class="p">();</span></div><div class='line' id='LC91'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">items</span> <span class="o">=</span> <span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span><span class="p">.</span><span class="nx">children</span><span class="p">();</span></div><div class='line' id='LC92'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">pagination</span> <span class="o">=</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC93'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span> <span class="o">=</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC94'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span> <span class="o">=</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC95'><br/></div><div class='line' id='LC96'>		<span class="p">},</span></div><div class='line' id='LC97'><br/></div><div class='line' id='LC98'>		<span class="nx">_addClasses</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC99'><br/></div><div class='line' id='LC100'>			<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="k">this</span><span class="p">.</span><span class="nx">oldClass</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC101'>				<span class="k">this</span><span class="p">.</span><span class="nx">oldClass</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">.</span><span class="nx">attr</span><span class="p">(</span><span class="s1">&#39;class&#39;</span><span class="p">);</span></div><div class='line' id='LC102'>			<span class="p">}</span></div><div class='line' id='LC103'><br/></div><div class='line' id='LC104'>			<span class="k">this</span><span class="p">.</span><span class="nx">_removeClasses</span><span class="p">();</span></div><div class='line' id='LC105'><br/></div><div class='line' id='LC106'>			<span class="kd">var</span> <span class="nx">baseClass</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">widgetBaseClass</span><span class="p">,</span></div><div class='line' id='LC107'>				<span class="nx">classes</span> <span class="o">=</span> <span class="p">[];</span></div><div class='line' id='LC108'><br/></div><div class='line' id='LC109'>			<span class="nx">classes</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">baseClass</span><span class="p">);</span></div><div class='line' id='LC110'>			<span class="nx">classes</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">baseClass</span> <span class="o">+</span> <span class="s1">&#39;-&#39;</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">orientation</span><span class="p">);</span></div><div class='line' id='LC111'>			<span class="nx">classes</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">baseClass</span> <span class="o">+</span> <span class="s1">&#39;-items-&#39;</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">itemsPerPage</span><span class="p">);</span></div><div class='line' id='LC112'>			<span class="nx">classes</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">baseClass</span> <span class="o">+</span> <span class="s1">&#39;-rows-&#39;</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">noOfRows</span><span class="p">);</span></div><div class='line' id='LC113'><br/></div><div class='line' id='LC114'>			<span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">.</span><span class="nx">addClass</span><span class="p">(</span><span class="nx">classes</span><span class="p">.</span><span class="nx">join</span><span class="p">(</span><span class="s1">&#39; &#39;</span><span class="p">));</span></div><div class='line' id='LC115'><br/></div><div class='line' id='LC116'>		<span class="p">},</span></div><div class='line' id='LC117'><br/></div><div class='line' id='LC118'>		<span class="c1">// removes ui-carousel* classes</span></div><div class='line' id='LC119'>		<span class="nx">_removeClasses</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC120'><br/></div><div class='line' id='LC121'>			<span class="kd">var</span> <span class="nx">uiClasses</span> <span class="o">=</span> <span class="p">[],</span></div><div class='line' id='LC122'>				<span class="nx">current</span><span class="p">,</span></div><div class='line' id='LC123'>				<span class="nx">fragments</span><span class="p">;</span></div><div class='line' id='LC124'><br/></div><div class='line' id='LC125'>			<span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">.</span><span class="nx">removeClass</span><span class="p">(</span><span class="kd">function</span> <span class="p">(</span><span class="nx">i</span><span class="p">,</span> <span class="nx">currentClasses</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC126'><br/></div><div class='line' id='LC127'>				<span class="nx">currentClasses</span> <span class="o">=</span> <span class="nx">currentClasses</span><span class="p">.</span><span class="nx">split</span><span class="p">(</span><span class="s1">&#39; &#39;</span><span class="p">);</span></div><div class='line' id='LC128'><br/></div><div class='line' id='LC129'>				<span class="nx">$</span><span class="p">.</span><span class="nx">each</span><span class="p">(</span><span class="nx">currentClasses</span><span class="p">,</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">i</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC130'><br/></div><div class='line' id='LC131'>					<span class="nx">current</span> <span class="o">=</span> <span class="nx">currentClasses</span><span class="p">[</span><span class="nx">i</span><span class="p">];</span></div><div class='line' id='LC132'>					<span class="nx">fragments</span> <span class="o">=</span> <span class="nx">current</span><span class="p">.</span><span class="nx">split</span><span class="p">(</span><span class="s1">&#39;-&#39;</span><span class="p">);</span></div><div class='line' id='LC133'><br/></div><div class='line' id='LC134'>					<span class="k">if</span> <span class="p">(</span><span class="nx">fragments</span><span class="p">[</span><span class="mi">0</span><span class="p">]</span> <span class="o">===</span> <span class="s1">&#39;ui&#39;</span> <span class="o">&amp;&amp;</span> <span class="nx">fragments</span><span class="p">[</span><span class="mi">1</span><span class="p">]</span> <span class="o">===</span> <span class="s1">&#39;carousel&#39;</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC135'>						<span class="nx">uiClasses</span><span class="p">.</span><span class="nx">push</span><span class="p">(</span><span class="nx">current</span><span class="p">);</span></div><div class='line' id='LC136'>					<span class="p">}</span></div><div class='line' id='LC137'><br/></div><div class='line' id='LC138'>				<span class="p">});</span></div><div class='line' id='LC139'><br/></div><div class='line' id='LC140'>				<span class="k">return</span> <span class="nx">uiClasses</span><span class="p">.</span><span class="nx">join</span><span class="p">(</span><span class="s1">&#39; &#39;</span><span class="p">);</span></div><div class='line' id='LC141'><br/></div><div class='line' id='LC142'>			<span class="p">});</span></div><div class='line' id='LC143'><br/></div><div class='line' id='LC144'>		<span class="p">},</span></div><div class='line' id='LC145'><br/></div><div class='line' id='LC146'>		<span class="c1">// defines obj to hold strings based on orientation for dynamic method calls</span></div><div class='line' id='LC147'>		<span class="nx">_defineOrientation</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC148'><br/></div><div class='line' id='LC149'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">orientation</span> <span class="o">===</span> <span class="s1">&#39;horizontal&#39;</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC150'>				<span class="k">this</span><span class="p">.</span><span class="nx">isHorizontal</span> <span class="o">=</span> <span class="kc">true</span><span class="p">;</span></div><div class='line' id='LC151'>				<span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span> <span class="o">=</span> <span class="nx">horizontal</span><span class="p">;</span></div><div class='line' id='LC152'>			<span class="p">}</span></div><div class='line' id='LC153'>			<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC154'>				<span class="k">this</span><span class="p">.</span><span class="nx">isHorizontal</span> <span class="o">=</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC155'>				<span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span> <span class="o">=</span> <span class="nx">vertical</span><span class="p">;</span></div><div class='line' id='LC156'>				<span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">noOfRows</span> <span class="o">=</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC157'>			<span class="p">}</span></div><div class='line' id='LC158'><br/></div><div class='line' id='LC159'>		<span class="p">},</span></div><div class='line' id='LC160'><br/></div><div class='line' id='LC161'>		<span class="c1">// adds masking div (aka clipper)</span></div><div class='line' id='LC162'>		<span class="nx">_addMask</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC163'><br/></div><div class='line' id='LC164'>			<span class="kd">var</span> <span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">;</span></div><div class='line' id='LC165'><br/></div><div class='line' id='LC166'>			<span class="k">if</span> <span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">mask</span><span class="p">.</span><span class="nx">length</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC167'>				<span class="k">return</span><span class="p">;</span></div><div class='line' id='LC168'>			<span class="p">}</span></div><div class='line' id='LC169'><br/></div><div class='line' id='LC170'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">mask</span> <span class="o">=</span> <span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span></div><div class='line' id='LC171'>				<span class="p">.</span><span class="nx">wrap</span><span class="p">(</span><span class="s1">&#39;&lt;div class=&quot;mask&quot; /&gt;&#39;</span><span class="p">)</span></div><div class='line' id='LC172'>				<span class="p">.</span><span class="nx">parent</span><span class="p">();</span></div><div class='line' id='LC173'><br/></div><div class='line' id='LC174'>			<span class="c1">// indicates whether mask was dynamically added or already existed in mark-up</span></div><div class='line' id='LC175'>			<span class="k">this</span><span class="p">.</span><span class="nx">maskAdded</span> <span class="o">=</span> <span class="kc">true</span><span class="p">;</span></div><div class='line' id='LC176'><br/></div><div class='line' id='LC177'>		<span class="p">},</span></div><div class='line' id='LC178'><br/></div><div class='line' id='LC179'>		<span class="c1">// sets maskDim to later detemine lastPos, // should be getter? keep it dynamic</span></div><div class='line' id='LC180'>		<span class="nx">_setMaskDim</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC181'><br/></div><div class='line' id='LC182'>			<span class="k">this</span><span class="p">.</span><span class="nx">maskDim</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">mask</span><span class="p">[</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">dim</span><span class="p">]();</span></div><div class='line' id='LC183'><br/></div><div class='line' id='LC184'>		<span class="p">},</span></div><div class='line' id='LC185'><br/></div><div class='line' id='LC186'>		<span class="c1">// sets itemDim to the dimension of first item incl. margin</span></div><div class='line' id='LC187'>		<span class="nx">_setItemDim</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC188'><br/></div><div class='line' id='LC189'>			<span class="c1">// is this ridiculous??</span></div><div class='line' id='LC190'>			<span class="k">this</span><span class="p">.</span><span class="nx">itemDim</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">items</span><span class="p">[</span><span class="s1">&#39;outer&#39;</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">dim</span><span class="p">.</span><span class="nx">charAt</span><span class="p">(</span><span class="mi">0</span><span class="p">).</span><span class="nx">toUpperCase</span><span class="p">()</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">dim</span><span class="p">.</span><span class="nx">slice</span><span class="p">(</span><span class="mi">1</span><span class="p">)](</span><span class="kc">true</span><span class="p">);</span></div><div class='line' id='LC191'><br/></div><div class='line' id='LC192'>		<span class="p">},</span></div><div class='line' id='LC193'><br/></div><div class='line' id='LC194'>		<span class="c1">// gets options.itemsPerPage. If set to not number it&#39;s calculated based on maskdim</span></div><div class='line' id='LC195'>		<span class="nx">_getItemsPerPage</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC196'><br/></div><div class='line' id='LC197'>			<span class="c1">// if itemsPerPage of type number don&#39;t dynamically calculate</span></div><div class='line' id='LC198'>			<span class="k">if</span> <span class="p">(</span><span class="k">typeof</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">itemsPerPage</span> <span class="o">===</span> <span class="s1">&#39;number&#39;</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC199'>				<span class="k">return</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">itemsPerPage</span><span class="p">;</span></div><div class='line' id='LC200'>			<span class="p">}</span></div><div class='line' id='LC201'><br/></div><div class='line' id='LC202'>			<span class="k">return</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">floor</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">maskDim</span> <span class="o">/</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemDim</span><span class="p">);</span></div><div class='line' id='LC203'><br/></div><div class='line' id='LC204'>		<span class="p">},</span></div><div class='line' id='LC205'><br/></div><div class='line' id='LC206'>		<span class="c1">// sets no of items, not neccesarily the literal number of items if more than one row</span></div><div class='line' id='LC207'>		<span class="nx">_setNoOfItems</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC208'><br/></div><div class='line' id='LC209'>			<span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">=</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">ceil</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">items</span><span class="p">.</span><span class="nx">length</span> <span class="o">/</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">noOfRows</span><span class="p">);</span></div><div class='line' id='LC210'><br/></div><div class='line' id='LC211'>			<span class="c1">// this ensures runner width is correctly calculated</span></div><div class='line' id='LC212'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">noOfRows</span> <span class="o">&gt;</span> <span class="mi">1</span> <span class="o">&amp;&amp;</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">&lt;</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerPage</span><span class="p">())</span> <span class="p">{</span></div><div class='line' id='LC213'>				<span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerPage</span><span class="p">();</span></div><div class='line' id='LC214'>			<span class="p">}</span></div><div class='line' id='LC215'><br/></div><div class='line' id='LC216'>		<span class="p">},</span></div><div class='line' id='LC217'><br/></div><div class='line' id='LC218'>		<span class="c1">// sets noOfPages</span></div><div class='line' id='LC219'>		<span class="nx">_setNoOfPages</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC220'><br/></div><div class='line' id='LC221'>			<span class="k">this</span><span class="p">.</span><span class="nx">noOfPages</span> <span class="o">=</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">ceil</span><span class="p">((</span><span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">-</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerPage</span><span class="p">())</span> <span class="o">/</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerTransition</span><span class="p">())</span> <span class="o">+</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC222'><br/></div><div class='line' id='LC223'>		<span class="p">},</span></div><div class='line' id='LC224'><br/></div><div class='line' id='LC225'>		<span class="nx">_getItemsPerTransition</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC226'><br/></div><div class='line' id='LC227'>		    <span class="k">if</span> <span class="p">(</span><span class="k">typeof</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">itemsPerTransition</span> <span class="o">===</span> <span class="s1">&#39;number&#39;</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC228'>				<span class="k">return</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">itemsPerTransition</span><span class="p">;</span></div><div class='line' id='LC229'>		    <span class="p">}</span></div><div class='line' id='LC230'><br/></div><div class='line' id='LC231'>		    <span class="k">return</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerPage</span><span class="p">();</span></div><div class='line' id='LC232'><br/></div><div class='line' id='LC233'>		<span class="p">},</span></div><div class='line' id='LC234'><br/></div><div class='line' id='LC235'>		<span class="c1">// sets runners width // perhaps arg should be actual width to set...?</span></div><div class='line' id='LC236'>		<span class="nx">_setRunnerWidth</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">noOfItems</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC237'><br/></div><div class='line' id='LC238'>			<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="k">this</span><span class="p">.</span><span class="nx">isHorizontal</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC239'>				<span class="k">return</span><span class="p">;</span></div><div class='line' id='LC240'>			<span class="p">}</span></div><div class='line' id='LC241'><br/></div><div class='line' id='LC242'>			<span class="c1">// allow noOfItems to be overwritten with arg, use case: when cloned items need to be included but shouldn&#39;t be part of this.noOfItems</span></div><div class='line' id='LC243'>			<span class="kd">var</span> <span class="nx">width</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemDim</span> <span class="o">*</span> <span class="p">(</span><span class="nx">noOfItems</span> <span class="o">||</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span><span class="p">);</span></div><div class='line' id='LC244'>			<span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">runner</span><span class="p">.</span><span class="nx">width</span><span class="p">(</span><span class="nx">width</span><span class="p">);</span></div><div class='line' id='LC245'><br/></div><div class='line' id='LC246'>		<span class="p">},</span></div><div class='line' id='LC247'><br/></div><div class='line' id='LC248'>		<span class="c1">// sets lastPos to ensure runner doesn&#39;t move beyond mask (allowing mask to be any width and the use of margins)</span></div><div class='line' id='LC249'>		<span class="nx">_setLastPos</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC250'><br/></div><div class='line' id='LC251'>			<span class="c1">// noOfrows means last theoretical item might not be the last item</span></div><div class='line' id='LC252'>			<span class="kd">var</span> <span class="nx">lastItem</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">items</span><span class="p">.</span><span class="nx">eq</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">-</span> <span class="mi">1</span><span class="p">);</span></div><div class='line' id='LC253'><br/></div><div class='line' id='LC254'>			<span class="k">if</span> <span class="p">(</span><span class="nx">lastItem</span><span class="p">.</span><span class="nx">length</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC255'>				<span class="k">this</span><span class="p">.</span><span class="nx">lastPos</span> <span class="o">=</span> <span class="nx">lastItem</span><span class="p">.</span><span class="nx">position</span><span class="p">()[</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">pos</span><span class="p">]</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemDim</span> <span class="o">-</span></div><div class='line' id='LC256'>					<span class="k">this</span><span class="p">.</span><span class="nx">maskDim</span> <span class="o">-</span> <span class="nb">parseInt</span><span class="p">(</span><span class="nx">lastItem</span><span class="p">.</span><span class="nx">css</span><span class="p">(</span><span class="s1">&#39;margin-&#39;</span> <span class="o">+</span> <span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">pos2</span><span class="p">),</span> <span class="mi">10</span><span class="p">);</span></div><div class='line' id='LC257'>			<span class="p">}</span></div><div class='line' id='LC258'><br/></div><div class='line' id='LC259'>		<span class="p">},</span></div><div class='line' id='LC260'><br/></div><div class='line' id='LC261'>		<span class="c1">// sets last logical item</span></div><div class='line' id='LC262'>		<span class="nx">_setLastItem</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC263'><br/></div><div class='line' id='LC264'>			<span class="k">this</span><span class="p">.</span><span class="nx">lastItem</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">-</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerPage</span><span class="p">()</span> <span class="o">-</span> <span class="mi">1</span><span class="p">);</span></div><div class='line' id='LC265'><br/></div><div class='line' id='LC266'>		<span class="p">},</span></div><div class='line' id='LC267'><br/></div><div class='line' id='LC268'>		<span class="c1">// adds pagination links and binds associated events</span></div><div class='line' id='LC269'>		<span class="nx">_addPagination</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC270'><br/></div><div class='line' id='LC271'>			<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">pagination</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC272'>				<span class="k">return</span><span class="p">;</span></div><div class='line' id='LC273'>			<span class="p">}</span></div><div class='line' id='LC274'><br/></div><div class='line' id='LC275'>			<span class="kd">var</span> <span class="nx">self</span> <span class="o">=</span> <span class="k">this</span><span class="p">,</span></div><div class='line' id='LC276'>				<span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">,</span></div><div class='line' id='LC277'>				<span class="nx">opts</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">,</span></div><div class='line' id='LC278'>				<span class="nx">links</span> <span class="o">=</span> <span class="p">[],</span></div><div class='line' id='LC279'>				<span class="nx">i</span><span class="p">;</span></div><div class='line' id='LC280'><br/></div><div class='line' id='LC281'>			<span class="k">this</span><span class="p">.</span><span class="nx">_removePagination</span><span class="p">();</span></div><div class='line' id='LC282'><br/></div><div class='line' id='LC283'>			<span class="k">for</span> <span class="p">(</span><span class="nx">i</span> <span class="o">=</span> <span class="mi">1</span><span class="p">;</span> <span class="nx">i</span> <span class="o">&lt;=</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfPages</span><span class="p">;</span> <span class="nx">i</span><span class="o">++</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC284'>				<span class="nx">links</span><span class="p">[</span><span class="nx">i</span><span class="p">]</span> <span class="o">=</span> <span class="s1">&#39;&lt;li&gt;&lt;a href=&quot;#page-&#39;</span> <span class="o">+</span> <span class="nx">i</span> <span class="o">+</span> <span class="s1">&#39;&quot;&gt;&#39;</span> <span class="o">+</span> <span class="nx">i</span> <span class="o">+</span> <span class="s1">&#39;&lt;/a&gt;&lt;/li&gt;&#39;</span><span class="p">;</span></div><div class='line' id='LC285'>			<span class="p">}</span></div><div class='line' id='LC286'><br/></div><div class='line' id='LC287'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">pagination</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span><span class="s1">&#39;&lt;ol class=&quot;pagination-links&quot; /&gt;&#39;</span><span class="p">)</span></div><div class='line' id='LC288'>				<span class="p">.</span><span class="nx">append</span><span class="p">(</span><span class="nx">links</span><span class="p">.</span><span class="nx">join</span><span class="p">(</span><span class="s1">&#39;&#39;</span><span class="p">))</span></div><div class='line' id='LC289'>				<span class="p">.</span><span class="nx">delegate</span><span class="p">(</span><span class="s1">&#39;a&#39;</span><span class="p">,</span> <span class="s1">&#39;click.carousel&#39;</span><span class="p">,</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC290'><br/></div><div class='line' id='LC291'>					<span class="nx">self</span><span class="p">.</span><span class="nx">goToPage</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">hash</span><span class="p">.</span><span class="nx">split</span><span class="p">(</span><span class="s1">&#39;-&#39;</span><span class="p">)[</span><span class="mi">1</span><span class="p">]);</span></div><div class='line' id='LC292'><br/></div><div class='line' id='LC293'>					<span class="k">return</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC294'><br/></div><div class='line' id='LC295'>				<span class="p">});</span></div><div class='line' id='LC296'><br/></div><div class='line' id='LC297'>			<span class="k">if</span> <span class="p">(</span><span class="nx">$</span><span class="p">.</span><span class="nx">isFunction</span><span class="p">(</span><span class="nx">opts</span><span class="p">.</span><span class="nx">insertPagination</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC298'>				<span class="nx">opts</span><span class="p">.</span><span class="nx">insertPagination</span><span class="p">.</span><span class="nx">apply</span><span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">pagination</span><span class="p">[</span><span class="mi">0</span><span class="p">]);</span></div><div class='line' id='LC299'>			<span class="p">}</span></div><div class='line' id='LC300'>			<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC301'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">pagination</span><span class="p">.</span><span class="nx">insertAfter</span><span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">mask</span><span class="p">);</span></div><div class='line' id='LC302'>			<span class="p">}</span></div><div class='line' id='LC303'><br/></div><div class='line' id='LC304'>		<span class="p">},</span></div><div class='line' id='LC305'><br/></div><div class='line' id='LC306'>		<span class="nx">_removePagination</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC307'><br/></div><div class='line' id='LC308'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">pagination</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC309'>				<span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">pagination</span><span class="p">.</span><span class="nx">remove</span><span class="p">();</span></div><div class='line' id='LC310'>				<span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">pagination</span> <span class="o">=</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC311'>			<span class="p">}</span></div><div class='line' id='LC312'><br/></div><div class='line' id='LC313'>		<span class="p">},</span></div><div class='line' id='LC314'><br/></div><div class='line' id='LC315'>		<span class="c1">// shows specific page (one based)</span></div><div class='line' id='LC316'>		<span class="nx">goToPage</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">pageIndex</span><span class="p">,</span> <span class="nx">animate</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC317'><br/></div><div class='line' id='LC318'>			<span class="kd">var</span> <span class="nx">itemIndex</span> <span class="o">=</span> <span class="p">(</span><span class="nx">pageIndex</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="o">*</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerTransition</span><span class="p">()</span> <span class="o">+</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC319'><br/></div><div class='line' id='LC320'>			<span class="k">this</span><span class="p">.</span><span class="nx">oldItemIndex</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC321'>			<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">=</span> <span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC322'>			<span class="k">this</span><span class="p">.</span><span class="nx">_slide</span><span class="p">(</span><span class="nx">animate</span><span class="p">);</span></div><div class='line' id='LC323'><br/></div><div class='line' id='LC324'>		<span class="p">},</span></div><div class='line' id='LC325'><br/></div><div class='line' id='LC326'>		<span class="c1">// shows specific item (one based)</span></div><div class='line' id='LC327'>		<span class="nx">goToItem</span><span class="o">:</span> <span class="kd">function</span><span class="p">(</span><span class="nx">itemIndex</span><span class="p">,</span> <span class="nx">animate</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC328'><br/></div><div class='line' id='LC329'>			<span class="k">if</span> <span class="p">(</span><span class="k">typeof</span> <span class="nx">itemIndex</span> <span class="o">!==</span> <span class="s1">&#39;number&#39;</span><span class="p">)</span> <span class="p">{</span> <span class="c1">// assume element or jQuery obj</span></div><div class='line' id='LC330'>				<span class="nx">itemIndex</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span><span class="nx">itemIndex</span><span class="p">).</span><span class="nx">index</span><span class="p">()</span> <span class="o">+</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC331'>			<span class="p">}</span></div><div class='line' id='LC332'><br/></div><div class='line' id='LC333'>			<span class="k">this</span><span class="p">.</span><span class="nx">oldItemIndex</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC334'>			<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">=</span> <span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC335'><br/></div><div class='line' id='LC336'>			<span class="k">this</span><span class="p">.</span><span class="nx">_slide</span><span class="p">(</span><span class="nx">animate</span><span class="p">);</span></div><div class='line' id='LC337'><br/></div><div class='line' id='LC338'>		<span class="p">},</span></div><div class='line' id='LC339'><br/></div><div class='line' id='LC340'>		<span class="c1">// adds next and prev links</span></div><div class='line' id='LC341'>		<span class="nx">_addNextPrevActions</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC342'><br/></div><div class='line' id='LC343'>			<span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">nextPrevActions</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC344'>				<span class="k">return</span><span class="p">;</span></div><div class='line' id='LC345'>			<span class="p">}</span></div><div class='line' id='LC346'><br/></div><div class='line' id='LC347'>			<span class="kd">var</span> <span class="nx">self</span> <span class="o">=</span> <span class="k">this</span><span class="p">,</span></div><div class='line' id='LC348'>				<span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">,</span></div><div class='line' id='LC349'>				<span class="nx">opts</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">;</span></div><div class='line' id='LC350'><br/></div><div class='line' id='LC351'>			<span class="k">this</span><span class="p">.</span><span class="nx">_removeNextPrevActions</span><span class="p">();</span></div><div class='line' id='LC352'><br/></div><div class='line' id='LC353'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span><span class="s1">&#39;&lt;a href=&quot;#&quot; class=&quot;prev&quot;&gt;Prev&lt;/a&gt;&#39;</span><span class="p">)</span></div><div class='line' id='LC354'>				<span class="p">.</span><span class="nx">bind</span><span class="p">(</span><span class="s1">&#39;click.carousel&#39;</span><span class="p">,</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC355'>					<span class="nx">self</span><span class="p">.</span><span class="nx">prev</span><span class="p">();</span></div><div class='line' id='LC356'>					<span class="k">return</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC357'>				<span class="p">});;</span></div><div class='line' id='LC358'><br/></div><div class='line' id='LC359'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span><span class="s1">&#39;&lt;a href=&quot;#&quot; class=&quot;next&quot;&gt;Next&lt;/a&gt;&#39;</span><span class="p">)</span></div><div class='line' id='LC360'>				<span class="p">.</span><span class="nx">bind</span><span class="p">(</span><span class="s1">&#39;click.carousel&#39;</span><span class="p">,</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC361'>					<span class="nx">self</span><span class="p">.</span><span class="nx">next</span><span class="p">();</span></div><div class='line' id='LC362'>					<span class="k">return</span> <span class="kc">false</span><span class="p">;</span></div><div class='line' id='LC363'>				<span class="p">});</span></div><div class='line' id='LC364'><br/></div><div class='line' id='LC365'>			<span class="k">if</span> <span class="p">(</span><span class="nx">$</span><span class="p">.</span><span class="nx">isFunction</span><span class="p">(</span><span class="nx">opts</span><span class="p">.</span><span class="nx">insertPrevAction</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC366'>				<span class="nx">opts</span><span class="p">.</span><span class="nx">insertPrevAction</span><span class="p">.</span><span class="nx">apply</span><span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span><span class="p">[</span><span class="mi">0</span><span class="p">]);</span></div><div class='line' id='LC367'>			<span class="p">}</span></div><div class='line' id='LC368'>			<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC369'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span><span class="p">.</span><span class="nx">appendTo</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">);</span></div><div class='line' id='LC370'>			<span class="p">}</span></div><div class='line' id='LC371'><br/></div><div class='line' id='LC372'>			<span class="k">if</span> <span class="p">(</span><span class="nx">$</span><span class="p">.</span><span class="nx">isFunction</span><span class="p">(</span><span class="nx">opts</span><span class="p">.</span><span class="nx">insertNextAction</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC373'>				<span class="nx">opts</span><span class="p">.</span><span class="nx">insertNextAction</span><span class="p">.</span><span class="nx">apply</span><span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span><span class="p">[</span><span class="mi">0</span><span class="p">]);</span></div><div class='line' id='LC374'>			<span class="p">}</span></div><div class='line' id='LC375'>			<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC376'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span><span class="p">.</span><span class="nx">appendTo</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">);</span></div><div class='line' id='LC377'>			<span class="p">}</span></div><div class='line' id='LC378'><br/></div><div class='line' id='LC379'>		<span class="p">},</span></div><div class='line' id='LC380'><br/></div><div class='line' id='LC381'>		<span class="nx">_removeNextPrevActions</span><span class="o">:</span> <span class="kd">function</span><span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC382'><br/></div><div class='line' id='LC383'>			<span class="kd">var</span> <span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">;</span></div><div class='line' id='LC384'><br/></div><div class='line' id='LC385'>			<span class="k">if</span> <span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC386'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span><span class="p">.</span><span class="nx">remove</span><span class="p">();</span></div><div class='line' id='LC387'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span> <span class="o">=</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC388'>			<span class="p">}</span>	</div><div class='line' id='LC389'><br/></div><div class='line' id='LC390'>			<span class="k">if</span> <span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC391'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span><span class="p">.</span><span class="nx">remove</span><span class="p">();</span></div><div class='line' id='LC392'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span> <span class="o">=</span> <span class="kc">null</span><span class="p">;</span></div><div class='line' id='LC393'>			<span class="p">}</span></div><div class='line' id='LC394'><br/></div><div class='line' id='LC395'>		<span class="p">},</span></div><div class='line' id='LC396'><br/></div><div class='line' id='LC397'>		<span class="c1">// moves to next page</span></div><div class='line' id='LC398'>		<span class="nx">next</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC399'><br/></div><div class='line' id='LC400'>			<span class="k">this</span><span class="p">.</span><span class="nx">oldItemIndex</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC401'>			<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">+=</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerTransition</span><span class="p">();</span></div><div class='line' id='LC402'>			<span class="k">this</span><span class="p">.</span><span class="nx">_slide</span><span class="p">();</span></div><div class='line' id='LC403'><br/></div><div class='line' id='LC404'>		<span class="p">},</span></div><div class='line' id='LC405'><br/></div><div class='line' id='LC406'>		<span class="c1">// moves to prev page</span></div><div class='line' id='LC407'>		<span class="nx">prev</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC408'><br/></div><div class='line' id='LC409'>			<span class="k">this</span><span class="p">.</span><span class="nx">oldItemIndex</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC410'>			<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">-=</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerTransition</span><span class="p">();</span></div><div class='line' id='LC411'>			<span class="k">this</span><span class="p">.</span><span class="nx">_slide</span><span class="p">();</span></div><div class='line' id='LC412'><br/></div><div class='line' id='LC413'>		<span class="p">},</span></div><div class='line' id='LC414'><br/></div><div class='line' id='LC415'>		<span class="c1">// updates pagination, next and prev link status classes</span></div><div class='line' id='LC416'>		<span class="nx">_updateUi</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC417'><br/></div><div class='line' id='LC418'>			<span class="kd">var</span> <span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">,</span></div><div class='line' id='LC419'>				<span class="nx">index</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">,</span></div><div class='line' id='LC420'><br/></div><div class='line' id='LC421'>				<span class="c1">// add void class if ui doesn&#39;t make sense - can then be either hidden or styled like disabled / current</span></div><div class='line' id='LC422'>				<span class="c1">// better than setting pagination to false as this senario isn&#39;t an &#39;option change&#39;</span></div><div class='line' id='LC423'>				<span class="nx">isVoid</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span> <span class="o">&lt;=</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerPage</span><span class="p">();</span></div><div class='line' id='LC424'><br/></div><div class='line' id='LC425'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">pagination</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC426'><br/></div><div class='line' id='LC427'>				<span class="k">if</span> <span class="p">(</span><span class="nx">isVoid</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC428'>					<span class="nx">elems</span><span class="p">.</span><span class="nx">pagination</span><span class="p">.</span><span class="nx">addClass</span><span class="p">(</span><span class="s1">&#39;void&#39;</span><span class="p">);</span></div><div class='line' id='LC429'>				<span class="p">}</span></div><div class='line' id='LC430'>				<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC431'>					<span class="nx">elems</span><span class="p">.</span><span class="nx">pagination</span></div><div class='line' id='LC432'>						<span class="p">.</span><span class="nx">children</span><span class="p">(</span><span class="s1">&#39;li&#39;</span><span class="p">)</span></div><div class='line' id='LC433'>							<span class="p">.</span><span class="nx">removeClass</span><span class="p">(</span><span class="s1">&#39;current&#39;</span><span class="p">)</span></div><div class='line' id='LC434'>							<span class="p">.</span><span class="nx">eq</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">_getPage</span><span class="p">()</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span></div><div class='line' id='LC435'>								<span class="p">.</span><span class="nx">addClass</span><span class="p">(</span><span class="s1">&#39;current&#39;</span><span class="p">);</span></div><div class='line' id='LC436'>				<span class="p">}</span></div><div class='line' id='LC437'><br/></div><div class='line' id='LC438'>			<span class="p">}</span></div><div class='line' id='LC439'><br/></div><div class='line' id='LC440'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">nextPrevActions</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC441'><br/></div><div class='line' id='LC442'>				<span class="kd">var</span> <span class="nx">nextPrev</span> <span class="o">=</span> <span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span><span class="p">.</span><span class="nx">add</span><span class="p">(</span><span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span><span class="p">);</span></div><div class='line' id='LC443'>				<span class="nx">nextPrev</span><span class="p">.</span><span class="nx">removeClass</span><span class="p">(</span><span class="s1">&#39;disabled&#39;</span><span class="p">);</span></div><div class='line' id='LC444'><br/></div><div class='line' id='LC445'>				<span class="k">if</span> <span class="p">(</span><span class="nx">isVoid</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC446'>					<span class="nx">nextPrev</span><span class="p">.</span><span class="nx">addClass</span><span class="p">(</span><span class="s1">&#39;void&#39;</span><span class="p">);</span>		</div><div class='line' id='LC447'>				<span class="p">}</span></div><div class='line' id='LC448'>				<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC449'>					<span class="nx">nextPrev</span><span class="p">.</span><span class="nx">removeClass</span><span class="p">(</span><span class="s1">&#39;void&#39;</span><span class="p">);</span></div><div class='line' id='LC450'><br/></div><div class='line' id='LC451'>					<span class="k">if</span> <span class="p">(</span><span class="nx">index</span> <span class="o">===</span> <span class="k">this</span><span class="p">.</span><span class="nx">lastItem</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC452'>						<span class="nx">elems</span><span class="p">.</span><span class="nx">nextAction</span><span class="p">.</span><span class="nx">addClass</span><span class="p">(</span><span class="s1">&#39;disabled&#39;</span><span class="p">);</span></div><div class='line' id='LC453'>					<span class="p">}</span></div><div class='line' id='LC454'>					<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">index</span> <span class="o">===</span> <span class="mi">1</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC455'>						<span class="nx">elems</span><span class="p">.</span><span class="nx">prevAction</span><span class="p">.</span><span class="nx">addClass</span><span class="p">(</span><span class="s1">&#39;disabled&#39;</span><span class="p">);</span></div><div class='line' id='LC456'>					<span class="p">}</span></div><div class='line' id='LC457'>				<span class="p">}</span></div><div class='line' id='LC458'>			<span class="p">}</span></div><div class='line' id='LC459'><br/></div><div class='line' id='LC460'>		<span class="p">},</span></div><div class='line' id='LC461'><br/></div><div class='line' id='LC462'>		<span class="c1">// return page based on itemIndex</span></div><div class='line' id='LC463'>		<span class="nx">_getPage</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">index</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC464'><br/></div><div class='line' id='LC465'>			<span class="c1">// allow index to be overwritten with arg, use case: when old page needs to be calculated after this.itemIndex has been modified</span></div><div class='line' id='LC466'>			<span class="c1">// see: before and after animate callbacks</span></div><div class='line' id='LC467'><br/></div><div class='line' id='LC468'>			<span class="c1">// check if undefined, as 0 === false</span></div><div class='line' id='LC469'>			<span class="nx">index</span> <span class="o">=</span> <span class="nx">index</span> <span class="o">!==</span> <span class="kc">undefined</span> <span class="o">?</span> <span class="nx">index</span> <span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">;</span></div><div class='line' id='LC470'>			<span class="nx">index</span> <span class="o">-=</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC471'><br/></div><div class='line' id='LC472'>			<span class="k">return</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">ceil</span><span class="p">(</span><span class="nx">index</span> <span class="o">/</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getItemsPerTransition</span><span class="p">())</span> <span class="o">+</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC473'><br/></div><div class='line' id='LC474'>		<span class="p">},</span></div><div class='line' id='LC475'><br/></div><div class='line' id='LC476'>		<span class="nx">_slide</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">animate</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC477'><br/></div><div class='line' id='LC478'>			<span class="kd">var</span> <span class="nx">self</span> <span class="o">=</span> <span class="k">this</span><span class="p">,</span></div><div class='line' id='LC479'>				<span class="nx">speed</span> <span class="o">=</span> <span class="nx">animate</span> <span class="o">===</span> <span class="kc">false</span> <span class="o">?</span> <span class="mi">0</span> <span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">speed</span><span class="p">,</span> <span class="c1">// default to animate</span></div><div class='line' id='LC480'>				<span class="nx">animateProps</span> <span class="o">=</span> <span class="p">{},</span></div><div class='line' id='LC481'>				<span class="nx">pos</span><span class="p">;</span></div><div class='line' id='LC482'><br/></div><div class='line' id='LC483'>			<span class="nx">pos</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getPos</span><span class="p">();</span></div><div class='line' id='LC484'><br/></div><div class='line' id='LC485'>			<span class="nx">animateProps</span><span class="p">[</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">pos</span><span class="p">]</span> <span class="o">=</span> <span class="o">-</span><span class="nx">pos</span><span class="p">;</span></div><div class='line' id='LC486'><br/></div><div class='line' id='LC487'>			<span class="k">this</span><span class="p">.</span><span class="nx">_trigger</span><span class="p">(</span><span class="s1">&#39;beforeAnimate&#39;</span><span class="p">,</span> <span class="kc">null</span><span class="p">,</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getData</span><span class="p">());</span></div><div class='line' id='LC488'><br/></div><div class='line' id='LC489'>			<span class="cm">/* CSS transitions perform very poorly (however &#39;translate3d()&#39; invokes hardware acceleration in iOS / webkit...)</span></div><div class='line' id='LC490'><span class="cm">			v. smooth, however means touch .draggable extension would have to be modified...</span></div><div class='line' id='LC491'><span class="cm">			elems.runner.css({</span></div><div class='line' id='LC492'><span class="cm">				left: -pos,</span></div><div class='line' id='LC493'><span class="cm">				transition: &#39;left .3s linear&#39;,</span></div><div class='line' id='LC494'><span class="cm">				&#39;-o-transition&#39;: &#39;left .400s linear&#39;,</span></div><div class='line' id='LC495'><span class="cm">				&#39;-moz-transition&#39;: &#39;left .400s linear&#39;,</span></div><div class='line' id='LC496'><span class="cm">				&#39;-webkit-transition&#39;: &#39;left .400s linear&#39;,</span></div><div class='line' id='LC497'><span class="cm">				&#39;-webkit-transition&#39;: &#39;left .400s linear&#39;,</span></div><div class='line' id='LC498'><span class="cm">				&#39;-moz-transition&#39;: &#39;left .400s linear&#39;</span></div><div class='line' id='LC499'><span class="cm">			});*/</span></div><div class='line' id='LC500'><br/></div><div class='line' id='LC501'>			<span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">runner</span></div><div class='line' id='LC502'>				<span class="p">.</span><span class="nx">stop</span><span class="p">()</span></div><div class='line' id='LC503'>				<span class="p">.</span><span class="nx">animate</span><span class="p">(</span><span class="nx">animateProps</span><span class="p">,</span> <span class="nx">speed</span><span class="p">,</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">.</span><span class="nx">easing</span><span class="p">,</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC504'><br/></div><div class='line' id='LC505'>					<span class="nx">self</span><span class="p">.</span><span class="nx">_trigger</span><span class="p">(</span><span class="s1">&#39;afterAnimate&#39;</span><span class="p">,</span> <span class="kc">null</span><span class="p">,</span> <span class="nx">self</span><span class="p">.</span><span class="nx">_getData</span><span class="p">());</span></div><div class='line' id='LC506'><br/></div><div class='line' id='LC507'>				<span class="p">});</span></div><div class='line' id='LC508'><br/></div><div class='line' id='LC509'>			<span class="k">this</span><span class="p">.</span><span class="nx">_updateUi</span><span class="p">();</span></div><div class='line' id='LC510'><br/></div><div class='line' id='LC511'>		<span class="p">},</span></div><div class='line' id='LC512'><br/></div><div class='line' id='LC513'>		<span class="c1">// gets items pos</span></div><div class='line' id='LC514'>		<span class="nx">_getPos</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC515'><br/></div><div class='line' id='LC516'>			<span class="kd">var</span> <span class="nx">pos</span><span class="p">;</span></div><div class='line' id='LC517'><br/></div><div class='line' id='LC518'>			<span class="c1">// check whether there are enough items to animate to</span></div><div class='line' id='LC519'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">&gt;</span> <span class="k">this</span><span class="p">.</span><span class="nx">lastItem</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC520'>				<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">lastItem</span><span class="p">;</span></div><div class='line' id='LC521'>			<span class="p">}</span></div><div class='line' id='LC522'>			<span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC523'>				<span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">=</span> <span class="mi">1</span><span class="p">;</span> <span class="c1">// go to first</span></div><div class='line' id='LC524'>			<span class="p">}</span></div><div class='line' id='LC525'><br/></div><div class='line' id='LC526'>			<span class="nx">pos</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">items</span><span class="p">.</span><span class="nx">eq</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span> <span class="o">-</span> <span class="mi">1</span><span class="p">).</span><span class="nx">position</span><span class="p">()[</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">pos</span><span class="p">];</span></div><div class='line' id='LC527'>			<span class="c1">// pos = this.itemIndex * this.itemDim;</span></div><div class='line' id='LC528'><br/></div><div class='line' id='LC529'>			<span class="c1">// check pos doesn&#39;t go past last</span></div><div class='line' id='LC530'>			<span class="k">if</span> <span class="p">(</span><span class="nx">pos</span> <span class="o">&gt;</span> <span class="k">this</span><span class="p">.</span><span class="nx">lastPos</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC531'>				<span class="nx">pos</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">lastPos</span><span class="p">;</span></div><div class='line' id='LC532'>			<span class="p">}</span></div><div class='line' id='LC533'><br/></div><div class='line' id='LC534'>			<span class="k">return</span> <span class="nx">pos</span><span class="p">;</span></div><div class='line' id='LC535'><br/></div><div class='line' id='LC536'>		<span class="p">},</span></div><div class='line' id='LC537'><br/></div><div class='line' id='LC538'>		<span class="c1">// returns obj with useful data to be passed into callback events</span></div><div class='line' id='LC539'>		<span class="nx">_getData</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC540'><br/></div><div class='line' id='LC541'>			<span class="k">return</span> <span class="p">{</span></div><div class='line' id='LC542'>				<span class="nx">index</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">,</span></div><div class='line' id='LC543'>				<span class="nx">page</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getPage</span><span class="p">(),</span></div><div class='line' id='LC544'>				<span class="nx">oldIndex</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">oldItemIndex</span><span class="p">,</span></div><div class='line' id='LC545'>				<span class="nx">oldPage</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">_getPage</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">oldItemIndex</span><span class="p">),</span></div><div class='line' id='LC546'>				<span class="nx">noOfItems</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfItems</span><span class="p">,</span></div><div class='line' id='LC547'>				<span class="nx">noOfPages</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">noOfPages</span><span class="p">,</span></div><div class='line' id='LC548'>				<span class="nx">elements</span><span class="o">:</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span></div><div class='line' id='LC549'>			<span class="p">}</span></div><div class='line' id='LC550'><br/></div><div class='line' id='LC551'>		<span class="p">},</span></div><div class='line' id='LC552'><br/></div><div class='line' id='LC553'>		<span class="c1">// refresh carousel</span></div><div class='line' id='LC554'>		<span class="nx">refresh</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">items</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC555'><br/></div><div class='line' id='LC556'>			<span class="c1">// re-cache items in case new items have been added</span></div><div class='line' id='LC557'>			<span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">items</span> <span class="o">=</span> <span class="nx">items</span> <span class="o">||</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">.</span><span class="nx">runner</span><span class="p">.</span><span class="nx">children</span><span class="p">(</span><span class="s1">&#39;li&#39;</span><span class="p">);</span></div><div class='line' id='LC558'><br/></div><div class='line' id='LC559'>			<span class="c1">// setup</span></div><div class='line' id='LC560'>			<span class="k">this</span><span class="p">.</span><span class="nx">_addClasses</span><span class="p">();</span></div><div class='line' id='LC561'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setMaskDim</span><span class="p">();</span></div><div class='line' id='LC562'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setItemDim</span><span class="p">();</span></div><div class='line' id='LC563'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setNoOfItems</span><span class="p">();</span></div><div class='line' id='LC564'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setRunnerWidth</span><span class="p">();</span></div><div class='line' id='LC565'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setLastPos</span><span class="p">();</span></div><div class='line' id='LC566'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setLastItem</span><span class="p">();</span></div><div class='line' id='LC567'><br/></div><div class='line' id='LC568'>			<span class="c1">// pagination</span></div><div class='line' id='LC569'>			<span class="k">this</span><span class="p">.</span><span class="nx">_setNoOfPages</span><span class="p">();</span></div><div class='line' id='LC570'>			<span class="k">this</span><span class="p">.</span><span class="nx">_addPagination</span><span class="p">();</span></div><div class='line' id='LC571'><br/></div><div class='line' id='LC572'>			<span class="k">this</span><span class="p">.</span><span class="nx">goToItem</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">itemIndex</span><span class="p">,</span> <span class="kc">false</span><span class="p">);</span></div><div class='line' id='LC573'><br/></div><div class='line' id='LC574'>			<span class="k">this</span><span class="p">.</span><span class="nx">_updateUi</span><span class="p">();</span></div><div class='line' id='LC575'><br/></div><div class='line' id='LC576'>		<span class="p">},</span></div><div class='line' id='LC577'><br/></div><div class='line' id='LC578'>		<span class="c1">// handles option updates</span></div><div class='line' id='LC579'>		<span class="nx">_setOption</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">option</span><span class="p">,</span> <span class="nx">value</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC580'><br/></div><div class='line' id='LC581'>			<span class="kd">var</span> <span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">,</span></div><div class='line' id='LC582'>				<span class="nx">opts</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">options</span><span class="p">;</span></div><div class='line' id='LC583'><br/></div><div class='line' id='LC584'>			<span class="nx">_super</span><span class="p">.</span><span class="nx">_setOption</span><span class="p">.</span><span class="nx">apply</span><span class="p">(</span><span class="k">this</span><span class="p">,</span> <span class="nx">arguments</span><span class="p">);</span></div><div class='line' id='LC585'><br/></div><div class='line' id='LC586'>			<span class="k">switch</span> <span class="p">(</span><span class="nx">option</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC587'><br/></div><div class='line' id='LC588'>			<span class="k">case</span> <span class="s1">&#39;itemsPerPage&#39;</span><span class="o">:</span></div><div class='line' id='LC589'><br/></div><div class='line' id='LC590'>				<span class="k">this</span><span class="p">.</span><span class="nx">refresh</span><span class="p">();</span></div><div class='line' id='LC591'><br/></div><div class='line' id='LC592'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC593'><br/></div><div class='line' id='LC594'>			<span class="k">case</span> <span class="s1">&#39;itemsPerTransition&#39;</span><span class="o">:</span></div><div class='line' id='LC595'><br/></div><div class='line' id='LC596'>				<span class="k">this</span><span class="p">.</span><span class="nx">refresh</span><span class="p">();</span></div><div class='line' id='LC597'><br/></div><div class='line' id='LC598'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC599'><br/></div><div class='line' id='LC600'>			<span class="k">case</span> <span class="s1">&#39;noOfRows&#39;</span><span class="o">:</span></div><div class='line' id='LC601'><br/></div><div class='line' id='LC602'>				<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">isHorizontal</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC603'>					<span class="k">this</span><span class="p">.</span><span class="nx">refresh</span><span class="p">();</span></div><div class='line' id='LC604'>				<span class="p">}</span></div><div class='line' id='LC605'>				<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC606'>					<span class="c1">// noOfRows must be 1 if vertical</span></div><div class='line' id='LC607'>					<span class="nx">opts</span><span class="p">.</span><span class="nx">noOfRows</span> <span class="o">=</span> <span class="mi">1</span><span class="p">;</span></div><div class='line' id='LC608'>				<span class="p">}</span></div><div class='line' id='LC609'><br/></div><div class='line' id='LC610'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC611'><br/></div><div class='line' id='LC612'>			<span class="k">case</span> <span class="s1">&#39;orientation&#39;</span><span class="o">:</span></div><div class='line' id='LC613'><br/></div><div class='line' id='LC614'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span><span class="p">.</span><span class="nx">width</span><span class="p">(</span><span class="s1">&#39;&#39;</span><span class="p">);</span></div><div class='line' id='LC615'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span><span class="p">.</span><span class="nx">css</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">pos</span><span class="p">,</span> <span class="s1">&#39;&#39;</span><span class="p">);</span></div><div class='line' id='LC616'>				<span class="k">this</span><span class="p">.</span><span class="nx">_defineOrientation</span><span class="p">();</span></div><div class='line' id='LC617'>				<span class="k">this</span><span class="p">.</span><span class="nx">refresh</span><span class="p">();</span></div><div class='line' id='LC618'><br/></div><div class='line' id='LC619'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC620'><br/></div><div class='line' id='LC621'>			<span class="k">case</span> <span class="s1">&#39;pagination&#39;</span><span class="o">:</span></div><div class='line' id='LC622'><br/></div><div class='line' id='LC623'>				<span class="k">if</span> <span class="p">(</span><span class="nx">value</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC624'>					<span class="k">this</span><span class="p">.</span><span class="nx">_addPagination</span><span class="p">();</span></div><div class='line' id='LC625'>					<span class="k">this</span><span class="p">.</span><span class="nx">_updateUi</span><span class="p">();</span></div><div class='line' id='LC626'>				<span class="p">}</span></div><div class='line' id='LC627'>				<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC628'>					<span class="k">this</span><span class="p">.</span><span class="nx">_removePagination</span><span class="p">();</span></div><div class='line' id='LC629'>				<span class="p">}</span></div><div class='line' id='LC630'><br/></div><div class='line' id='LC631'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC632'><br/></div><div class='line' id='LC633'>			<span class="k">case</span> <span class="s1">&#39;nextPrevActions&#39;</span><span class="o">:</span></div><div class='line' id='LC634'><br/></div><div class='line' id='LC635'>				<span class="k">if</span> <span class="p">(</span><span class="nx">value</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC636'>					<span class="k">this</span><span class="p">.</span><span class="nx">_addNextPrevActions</span><span class="p">();</span></div><div class='line' id='LC637'>					<span class="k">this</span><span class="p">.</span><span class="nx">_updateUi</span><span class="p">();</span></div><div class='line' id='LC638'>				<span class="p">}</span></div><div class='line' id='LC639'>				<span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC640'>					<span class="k">this</span><span class="p">.</span><span class="nx">_removeNextPrevActions</span><span class="p">();</span></div><div class='line' id='LC641'>				<span class="p">}</span></div><div class='line' id='LC642'><br/></div><div class='line' id='LC643'>				<span class="k">break</span><span class="p">;</span></div><div class='line' id='LC644'><br/></div><div class='line' id='LC645'>			<span class="p">}</span></div><div class='line' id='LC646'><br/></div><div class='line' id='LC647'>		<span class="p">},</span></div><div class='line' id='LC648'><br/></div><div class='line' id='LC649'>		<span class="c1">// returns carousel to original state</span></div><div class='line' id='LC650'>		<span class="nx">destroy</span><span class="o">:</span> <span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC651'><br/></div><div class='line' id='LC652'>			<span class="kd">var</span> <span class="nx">elems</span> <span class="o">=</span> <span class="k">this</span><span class="p">.</span><span class="nx">elements</span><span class="p">,</span></div><div class='line' id='LC653'>				<span class="nx">cssProps</span> <span class="o">=</span> <span class="p">{};</span></div><div class='line' id='LC654'><br/></div><div class='line' id='LC655'>			<span class="k">this</span><span class="p">.</span><span class="nx">element</span><span class="p">.</span><span class="nx">removeClass</span><span class="p">().</span><span class="nx">addClass</span><span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">oldClass</span><span class="p">);</span></div><div class='line' id='LC656'><br/></div><div class='line' id='LC657'>			<span class="k">if</span> <span class="p">(</span><span class="k">this</span><span class="p">.</span><span class="nx">maskAdded</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC658'>				<span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span></div><div class='line' id='LC659'>					<span class="p">.</span><span class="nx">unwrap</span><span class="p">(</span><span class="s1">&#39;.mask&#39;</span><span class="p">);</span></div><div class='line' id='LC660'>			<span class="p">}</span></div><div class='line' id='LC661'><br/></div><div class='line' id='LC662'>			<span class="nx">cssProps</span><span class="p">[</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">pos</span><span class="p">]</span> <span class="o">=</span> <span class="s1">&#39;&#39;</span><span class="p">;</span></div><div class='line' id='LC663'>			<span class="nx">cssProps</span><span class="p">[</span><span class="k">this</span><span class="p">.</span><span class="nx">helperStr</span><span class="p">.</span><span class="nx">dim</span><span class="p">]</span> <span class="o">=</span> <span class="s1">&#39;&#39;</span><span class="p">;</span></div><div class='line' id='LC664'>			<span class="nx">elems</span><span class="p">.</span><span class="nx">runner</span><span class="p">.</span><span class="nx">css</span><span class="p">(</span><span class="nx">cssProps</span><span class="p">);</span></div><div class='line' id='LC665'><br/></div><div class='line' id='LC666'>			<span class="k">this</span><span class="p">.</span><span class="nx">_removePagination</span><span class="p">();</span></div><div class='line' id='LC667'>			<span class="k">this</span><span class="p">.</span><span class="nx">_removeNextPrevActions</span><span class="p">();</span></div><div class='line' id='LC668'><br/></div><div class='line' id='LC669'>			<span class="nx">_super</span><span class="p">.</span><span class="nx">destroy</span><span class="p">.</span><span class="nx">apply</span><span class="p">(</span><span class="k">this</span><span class="p">,</span> <span class="nx">arguments</span><span class="p">);</span></div><div class='line' id='LC670'><br/></div><div class='line' id='LC671'>		<span class="p">}</span></div><div class='line' id='LC672'><br/></div><div class='line' id='LC673'>	<span class="p">});</span></div><div class='line' id='LC674'><br/></div><div class='line' id='LC675'>	<span class="nx">$</span><span class="p">.</span><span class="nx">ui</span><span class="p">.</span><span class="nx">carousel</span><span class="p">.</span><span class="nx">version</span> <span class="o">=</span> <span class="s1">&#39;0.7.5&#39;</span><span class="p">;</span></div><div class='line' id='LC676'><br/></div><div class='line' id='LC677'><span class="p">})(</span><span class="nx">jQuery</span><span class="p">);</span></div></pre></div>
              
            
          </td>
        </tr>
      </table>
    
  </div>


          </div>
        </div>
      </div>
    </div>
  

  </div>


<div class="frame frame-loading" style="display:none;">
  <img src="https://a248.e.akamai.net/assets.github.com/images/modules/ajax/big_spinner_336699.gif" height="32" width="32">
</div>

    </div>
  
      
    </div>

    <!--**************
     FOOTER
     **************-->
    <div id="footer" >
      <div class="upper_footer">
        <div class="site" class="clearfix">

        <!--[if IE]><h4 id="blacktocat_ie">GitHub Links</h4><![endif]-->
        <![if !IE]><h4 id="blacktocat">GitHub Links</h4><![endif]>

        <ul class="footer_nav">
          <h4>GitHub</h4>
          <li><a href="https://github.com/about">About</a></li>
          <li><a href="https://github.com/blog">Blog</a></li>
          <li><a href="https://github.com/contact">Contact &amp; Support</a></li>
          <li><a href="https://github.com/training">Training</a></li>
          <li><a href="http://status.github.com/">Site Status</a></li>
        </ul>

        <ul class="footer_nav">
          <h4>Tools</h4>
          <li><a href="http://mac.github.com/">GitHub for Mac</a></li>
          <li><a href="https://gist.github.com">Gist</a></li>
          <li><a href="http://fi.github.com/">Enterprise Install</a></li>
          <li><a href="http://jobs.github.com/">Job Board</a></li>
        </ul>

        <ul class="footer_nav">
          <h4>Extras</h4>
          <li><a href="http://shop.github.com/">Shop</a></li>
          <li><a href="http://octodex.github.com/">The Octodex</a></li>
        </ul>

        <ul class="footer_nav">
          <h4>Documentation</h4>
          <li><a href="http://help.github.com/">GitHub Help</a></li>
          <li><a href="http://developer.github.com/">Developer API</a></li>
          <li><a href="http://github.github.com/github-flavored-markdown/">GitHub Flavored Markdown</a></li>
          <li><a href="http://pages.github.com/">GitHub Pages</a></li>
        </ul>

        </div><!-- /.site -->
      </div><!-- /.upper_footer -->

      <div class="lower_footer">
        <div class="site" class="clearfix">

        <!--[if IE]><div id="legal_ie"><![endif]-->
        <![if !IE]><div id="legal"><![endif]>
              <ul>
                <li><a href="https://github.com/site/terms">Terms of Service</a></li>
                <li><a href="https://github.com/site/privacy">Privacy</a></li>
                <li><a href="https://github.com/security">Security</a></li>
              </ul>

              <p>&copy; 2011 <span id="_rrt" title="0.06933s from fe4.rs.github.com">GitHub</span> Inc. All rights reserved.</p>
            </div><!-- /#legal or /#legal_ie-->

          
          <div class="sponsor">
              <a href="http://www.rackspace.com" class="logo">
                <img alt="Dedicated Server" height="36" src="https://a248.e.akamai.net/assets.github.com/images/modules/footer/rackspace_logo.png?v2" width="38" />
              </a>
              Powered by the <a href="http://www.rackspace.com ">Dedicated
              Servers</a> and<br/> <a href="http://www.rackspacecloud.com">Cloud
              Computing</a> of Rackspace Hosting<span>&reg;</span>
          </div>
          
        </div><!-- /.site -->
      </div><!-- /.lower_footer -->
    </div><!-- /#footer -->

    

<div id="keyboard_shortcuts_pane" class="instapaper_ignore readability-extra" style="display:none">
  <h2>Keyboard Shortcuts <small><a href="#" class="js-see-all-keyboard-shortcuts">(see all)</a></small></h2>

  <div class="columns threecols">
    <div class="column first">
      <h3>Site wide shortcuts</h3>
      <dl class="keyboard-mappings">
        <dt>s</dt>
        <dd>Focus site search</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>?</dt>
        <dd>Bring up this help dialog</dd>
      </dl>
    </div><!-- /.column.first -->

    <div class="column middle" style='display:none'>
      <h3>Commit list</h3>
      <dl class="keyboard-mappings">
        <dt>j</dt>
        <dd>Move selected down</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>k</dt>
        <dd>Move selected up</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>t</dt>
        <dd>Open tree</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>p</dt>
        <dd>Open parent</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>c <em>or</em> o <em>or</em> enter</dt>
        <dd>Open commit</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>y</dt>
        <dd>Expand URL to its canonical form</dd>
      </dl>
    </div><!-- /.column.first -->

    <div class="column last" style='display:none'>
      <h3>Pull request list</h3>
      <dl class="keyboard-mappings">
        <dt>j</dt>
        <dd>Move selected down</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>k</dt>
        <dd>Move selected up</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>o <em>or</em> enter</dt>
        <dd>Open issue</dd>
      </dl>
    </div><!-- /.columns.last -->

  </div><!-- /.columns.equacols -->

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Issues</h3>

    <div class="columns threecols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt>j</dt>
          <dd>Move selected down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>k</dt>
          <dd>Move selected up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>x</dt>
          <dd>Toggle select target</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>o <em>or</em> enter</dt>
          <dd>Open issue</dd>
        </dl>
      </div><!-- /.column.first -->
      <div class="column middle">
        <dl class="keyboard-mappings">
          <dt>I</dt>
          <dd>Mark selected as read</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>U</dt>
          <dd>Mark selected as unread</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>e</dt>
          <dd>Close selected</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>y</dt>
          <dd>Remove selected from view</dd>
        </dl>
      </div><!-- /.column.middle -->
      <div class="column last">
        <dl class="keyboard-mappings">
          <dt>c</dt>
          <dd>Create issue</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>l</dt>
          <dd>Create label</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>i</dt>
          <dd>Back to inbox</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>u</dt>
          <dd>Back to issues</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>/</dt>
          <dd>Focus issues search</dd>
        </dl>
      </div>
    </div>
  </div>

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Network Graph</h3>
    <div class="columns equacols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt><span class="badmono">←</span> <em>or</em> h</dt>
          <dd>Scroll left</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">→</span> <em>or</em> l</dt>
          <dd>Scroll right</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">↑</span> <em>or</em> k</dt>
          <dd>Scroll up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">↓</span> <em>or</em> j</dt>
          <dd>Scroll down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>t</dt>
          <dd>Toggle visibility of head labels</dd>
        </dl>
      </div><!-- /.column.first -->
      <div class="column last">
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">←</span> <em>or</em> shift h</dt>
          <dd>Scroll all the way left</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">→</span> <em>or</em> shift l</dt>
          <dd>Scroll all the way right</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">↑</span> <em>or</em> shift k</dt>
          <dd>Scroll all the way up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">↓</span> <em>or</em> shift j</dt>
          <dd>Scroll all the way down</dd>
        </dl>
      </div><!-- /.column.last -->
    </div>
  </div>

  <div >
    <div class="rule"></div>
    <div class="columns threecols">
      <div class="column first" >
        <h3>Source Code Browsing</h3>
        <dl class="keyboard-mappings">
          <dt>t</dt>
          <dd>Activates the file finder</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>l</dt>
          <dd>Jump to line</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>y</dt>
          <dd>Expand URL to its canonical form</dd>
        </dl>
      </div>
    </div>
  </div>
</div>

    <div id="markdown-help" class="instapaper_ignore readability-extra">
  <h2>Markdown Cheat Sheet</h2>

  <div class="cheatsheet-content">

  <div class="mod">
    <div class="col">
      <h3>Format Text</h3>
      <p>Headers</p>
      <pre>
# This is an &lt;h1&gt; tag
## This is an &lt;h2&gt; tag
###### This is an &lt;h6&gt; tag</pre>
     <p>Text styles</p>
     <pre>
*This text will be italic*
_This will also be italic_
**This text will be bold**
__This will also be bold__

*You **can** combine them*
</pre>
    </div>
    <div class="col">
      <h3>Lists</h3>
      <p>Unordered</p>
      <pre>
* Item 1
* Item 2
  * Item 2a
  * Item 2b</pre>
     <p>Ordered</p>
     <pre>
1. Item 1
2. Item 2
3. Item 3
   * Item 3a
   * Item 3b</pre>
    </div>
    <div class="col">
      <h3>Miscellaneous</h3>
      <p>Images</p>
      <pre>
![GitHub Logo](/images/logo.png)
Format: ![Alt Text](url)
</pre>
     <p>Links</p>
     <pre>
http://github.com - automatic!
[GitHub](http://github.com)</pre>
<p>Blockquotes</p>
     <pre>
As Kanye West said:
> We're living the future so
> the present is our past.
</pre>
    </div>
  </div>
  <div class="rule"></div>

  <h3>Code Examples in Markdown</h3>
  <div class="col">
      <p>Syntax highlighting with <a href="http://github.github.com/github-flavored-markdown/" title="GitHub Flavored Markdown" target="_blank">GFM</a></p>
      <pre>
```javascript
function fancyAlert(arg) {
  if(arg) {
    $.facebox({div:'#foo'})
  }
}
```</pre>
    </div>
    <div class="col">
      <p>Or, indent your code 4 spaces</p>
      <pre>
Here is a Python code example
without syntax highlighting:

    def foo:
      if not bar:
        return true</pre>
    </div>
    <div class="col">
      <p>Inline code for comments</p>
      <pre>
I think you should use an
`&lt;addr&gt;` element here instead.</pre>
    </div>
  </div>

  </div>
</div>
    

    <!--[if IE 8]>
    <script type="text/javascript" charset="utf-8">
      $(document.body).addClass("ie8")
    </script>
    <![endif]-->

    <!--[if IE 7]>
    <script type="text/javascript" charset="utf-8">
      $(document.body).addClass("ie7")
    </script>
    <![endif]-->

    
    
    
    <script type="text/javascript">(function(){var d=document;var e=d.createElement("script");e.async=true;e.src="https://d1ros97qkrwjf5.cloudfront.net/14/eum/rum.js	";e.type="text/javascript";var s=d.getElementsByTagName("script")[0];s.parentNode.insertBefore(e,s);})();NREUMQ.push(["nrf2","beacon-1.newrelic.com","2f94e4d8c2",64799,"dw1bEBZcX1RWRhoEClsAGhcMXEQ=",0.0,63,new Date().getTime()])</script>
  </body>
</html>

