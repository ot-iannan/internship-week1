# internship-week1

Git vs GitHub



Git is a version control system that runs on your computer and tracks changes

to your files over time.

GitHub is a website that hosts Git repositories online, so you can back up your work and share it with others.

In short, Git is the tool; while GitHub is a place to store and collaborate on projects that use that tool.

You can use Git without GitHub, but GitHub needs Git to work.



\*Day 1 Progress (commit 1)

Practicing the core Git workflow. Using status, add, commit, clone.



Commit 2

Learning how to use diff, log, and restore.



&#x20;Day 1 Task 4 - Branches and Recovery



\- Created a feature branch (feature-day1), made a commit, merged it into main.

\- Created a deliberate merge conflict between main and conflict-practice branches

&#x20; by editing the same line in notes.md on both branches.

\- Resolved the conflict by manually editing notes.md and committing the fix.

\- Practiced git stash to temporarily save uncommitted changes, then git stash pop

&#x20; to bring them back.





\## Day 5 - Docker Setup Command Log



\### Setup

\- Installed Docker Desktop, enabled WSL2 with Ubuntu distro

\- Added Windows user to docker-users group to resolve permission errors

\- Confirmed engine running with: docker ps



\### Commands practiced



| Command | What it does |

|---|---|

| docker run hello-world | Pulled and ran the hello-world test image, confirming Docker works end-to-end |

| docker images | Listed all locally downloaded images |

| docker pull ubuntu | Downloaded the ubuntu image without running a container |

| docker run -it ubuntu bash | Ran an interactive container, entered a live Bash shell inside it |

| docker run -d --name mycontainer ubuntu sleep 300 | Ran a container in the background for 5 minutes |

| docker ps | Listed currently running containers |

| docker exec mycontainer echo "Hello from inside!" | Ran a command inside an already-running container |

| docker logs mycontainer | Viewed a container's output history |

| docker stop mycontainer | Stopped a running container |

| docker rm mycontainer | Removed a stopped container |

| docker rmi hello-world | Removed an image (after first removing any containers still referencing it) |



\### Notes

\- Images cannot be removed while a container (even stopped) still references them -

&#x20; the container must be removed first with docker rm, then the image with docker rmi.





\## Day 5 - Images \& Containers



\### What is an Image?

A Docker image is a read-only blueprint containing everything needed to run

something - code, runtime, libraries, and settings. It doesn't run by itself;

it's a fixed template used to create containers.



\### What is a Container?

A container is a running (or stopped) instance created from an image. Many

containers can be created from the same image, and each one runs independently,

with its own isolated process and memory.



\### Image -> Container Lifecycle



\[Image on Docker Hub]

&#x20;       |

&#x20;  docker pull

&#x20;       v

\[Image downloaded locally]        <- docker images

&#x20;       |

&#x20;  docker run

&#x20;       v

\[Container created \& running]     <- docker ps

&#x20;       |

&#x20;  docker stop

&#x20;       v

\[Container stopped, still exists] <- docker ps -a

&#x20;       |

&#x20;  docker rm

&#x20;       v

\[Container deleted]





(Separately: docker rmi deletes the image itself, but only once no

containers still reference it.)



\### Containers vs Virtual Machines



| | Container | Virtual Machine |

|---|---|---|

| Virtualizes | Just the application layer | An entire computer, including its own OS |

| Startup time | Seconds | Minutes |

| Size | Lightweight (MBs) | Heavy (GBs) |

| Resource use | Shares the host machine's OS kernel | Runs a full separate OS |

| Isolation | Process-level | Full hardware-level |



A container is like giving guests their own room in the same house (sharing

the foundation and utilities), while a VM is like building each guest a

separate house from scratch.



\### Inspecting

\- docker inspect ubuntu - showed image metadata (layers, config, creation date)

\- docker inspect inspect-test - showed a running container's status, network

&#x20; settings, and configuration

