# Application-Programming-Task
Java function to determine number of days since last RSS feed update.

Given task:
*Task*: Given a Dictionary keyed by Company and valued by RSS feed URL, write a function that determines which companies had no activity for a given number of days.
*Evaluation*: Your submission is evaluated as the limit of your ability to follow directions and deliver production code.
*Resources*: https://rss.com/popular-rss-feeds/ provides many example companies and their RSS feeds
*How to submit*: Provide a link to a GitHub repo
*Hints*:
  • Some companies might have more than one feed.
  • Our code has unit tests.
  • Our code is made in incremental commits and with meaningful comments.
  • Reply with questions if necessary, or document assumptions used to make progress.

*Assumtions*
  • Dictionary will be structured as:
           key : value
      company1 : [feed1, feed2, feed3]
      company2 : [feed1, feed2]
      ...
      
  • rss format = lastBuildDate (Used to determine the last update time)
    rdf format = syn:updateBase (Used to determine the last update time)
    
    
