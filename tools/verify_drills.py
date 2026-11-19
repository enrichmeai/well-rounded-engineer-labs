#!/usr/bin/env python3
"""CI check for every drill: the starter code must FAIL its tests, and the solution must PASS them.

A drill whose starter passes teaches nothing; a drill whose solution fails is broken. Both are caught here.

    python3 tools/verify_drills.py            every drill, Python and Java
    python3 tools/verify_drills.py 02         one drill
"""

import shutil
import subprocess
import sys
import tempfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT))
import practice  # noqa: E402


def check(d: Path, lang: str) -> list[str]:
    problems = []
    src = d / lang
    solution = ROOT / "solutions" / practice.drill_id(d) / lang
    for label, want_pass in (("starter", False), ("solution", True)):
        with tempfile.TemporaryDirectory() as tmp:
            work = Path(tmp) / lang
            shutil.copytree(src, work)
            if want_pass:
                for f in solution.iterdir():
                    shutil.copy(f, work / f.name)
            print(f"--- {practice.drill_id(d)} {lang} {label} (expect {'PASS' if want_pass else 'FAIL'})", flush=True)
            passed = practice.run_tests(d, lang == "java", folder=work)
            if passed != want_pass:
                problems.append(f"{practice.drill_id(d)} {lang} {label}: {'passed' if passed else 'failed'}, "
                                f"expected it to {'pass' if want_pass else 'fail'}")
    return problems


def main() -> None:
    wanted = sys.argv[1] if len(sys.argv) > 1 else None
    problems = []
    for d in practice.drills():
        if wanted and wanted not in (d.name, d.name[:2]):
            continue
        for lang in ("python", "java"):
            problems += check(d, lang)
    print()
    if problems:
        print("PROBLEMS:\n  " + "\n  ".join(problems))
        sys.exit(1)
    print("every starter fails and every solution passes")


if __name__ == "__main__":
    main()
