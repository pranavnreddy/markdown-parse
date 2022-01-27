## Code Fixes

![code_changes.png](code_changes.png)

Unfortunately, our group did not save each fix for each bug separately. Instead the fixes were all combined together into 1 commit. Since this code also worked for all the test examples after the 2nd test case, there isn't really anything to add.
I will, however, link to the original files that caused these breaks. The files have been renamed to better describe their test cases. The summaries of the issue and the fix are written as best as I could from memory and interpreting backwards from the code.

## [Original test file](test-file.md)

This file had two links, and the program worked for each of these. The original program had several issues however:

1. Images were treated as links, and this was undesirable.
2. Files with brackets `[]` but no parentheses `()` would cause index out of bounds exceptions.
3. 
3. Files with separated brackets `[]` and parentheses `()`  would be incorrectly interpreted as links.
4. It was possible for mismatched brackets/parentheses to cause infinite loops.
5. Links on the first line caused infinite loops.
6. Files with neither brackets `[]` nor parentheses `()` would cause index out of bounds exceptions.

## [No links](no_links.md)
![no_links_before_fix](no_links_before_fix.png)
This was a file that lacked links.

## [image_link.md](image_link.md)