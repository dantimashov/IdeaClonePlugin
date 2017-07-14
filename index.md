This is a plugin for an IDE IntelliJ IDEA 2017+. 
It provides on-the-fly inspection in a source code.

#### Main features

- Searching exact clones and clones with renamed tokens
- Analyzing huge projects (millions lines of code)
- Providing clone inspections on-the-fly
- Analyzing Java/Kotlin files

#### Warning

> Plugin requires additional memory up to 500 Mb per 1 million LOC

#### General usage instructions
* * *

#### Inspections

Since the plugin is installed inspection is available. 
Use **Alt + Enter** to see all duplicated fragments.

![](https://github.com/suhininalex/IdeaClonePlugin/blob/gh-pages/images/inspection.png?raw=true)

##### Settings

Use **File \| Settings** to setup plugin.

> Any change can cause a full reindexing of the current project

![](https://github.com/suhininalex/IdeaClonePlugin/blob/gh-pages/images/find-configuration.png?raw=true)

#### Comparision with other tools
* * *

| Tool                                | Commercial | On-the-fly | Recall     | Scalability |
|:------------------------------------|:-----------|:-----------|:-----------|:------------|
| IntelliJ IDEA Ultimate (action)     | yes        | no         | excellent  | acceptable  |
| IntelliJ IDEA Ultimate (inspection) | yes        | yes        | acceptable | excellent   |
| IDEA clone plugin                   | no         | yes        | good       | good        |

<!---  
| PMD                                 | no         | no         | ?          | ?           |
| Checkstyle                          | no         | no         | ?          | ?           |
| Duplicate finder maven plugin       | no         | no         | ?          | ?           |

# ###### PMD

###### Checkstyle

###### Duplicate finder maven plugin

###### IntelliJ IDEA Ultimate

###### IDEA clone plugin

--->

#### Tested projects
* * *

This plugin has been used to analyze several popular Java projects. 

| Project                 | Time (indexing)   | Time (action) | Memory  | Clones found  |
|:------------------------|:------------------|:--------------|:--------|:--------------|
| JetBrains MPS           | 220 s             | 160 s         | 2950 Mb | 8109          |
| Android Framework Base  | 145 s             | 38 s          | 1495 Mb | 5055          |
| JetBrains IntelliJ IDEA | 221 s             | 49 s          | 2210 Mb | 5178          |
| Apache Hadoop           | 54 s              | 13 s          | 799 Mb  | 1753          |
| Spring Framework        | 35 s              | 3 s           | 403 Mb  | 746           |
| Consulo                 | 86 s              | 18 s          | 869 Mb  | 2275          |
| Apache CloudStack       | 44 s              | 7 s           | 841 Mb  | 2875          |
| Apache Camel            | 31 s              | 8 s           | 640 Mb  | 1992          |

> Time (indexing) - time required to build a project index (necessary for clone inspection)

> Memory - total memory used by IntelliJ IDEA after full project indexing

###### Used parameters

![](https://github.com/suhininalex/IdeaClonePlugin/blob/gh-pages/images/test-configuration.png?raw=true)

###### Configuration

> CPU i5-760 lynfield (4 core, 2.8 Ghz) 

> Max heap: 4000 Mb