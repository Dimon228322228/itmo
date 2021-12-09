def n2bool(expr):
  return int(expr > 0)
  


def calc_func(f_inp, short=True):
    x1, x2, x3, x4, x5 = map(int, list(f_inp))
    nx1, nx2, nx3, nx4, nx5 = list(not(x) for x in list(f_inp))

    mknf = lambda: (x1+nx2+x3)*(x3+x4+nx5)*(x1+x3+nx4)*(nx1+x4+x5)*(nx1+nx3+nx4+nx5)*(nx1+x2+x3+x5)*(nx1+nx2+nx3+nx4)
    
    phi = lambda: n2bool(nx2*nx3)
    nphi = lambda: int(not(phi()))

    # mknf_short = lambda: (x1+x3+nx2*x4)*(nx1+ (x5+(x4*(x2+x3)))*(nx3+nx4+nx2*nx3) )
    mknf_short = lambda: (x1+x3+nx2*x4)*(nx1+ (x5+(x4*nphi())))*(nx3+nx4+phi())

    return n2bool(mknf_short())
    # return n2bool(mknf())


funcs = [ "00001", "00010", "01000", "10000", "00011", "01001", "01010", "10001", "10010", "11000", "01011", "11001", "11100", "10111", "11110", "11111", ]

for f in funcs:
    res1 = calc_func(f, False)
    res2 = calc_func(f, True)
    if res1 != res2:
        print("Failed at ", f)
    # print(calc_func(f))
    # phi = lambda:  
    # mdnf = n2bool(nx1*x3+x3*nx4+nx2*x3*nx5+nx1*nx2*nx4*nx5+x1*nx3*x4*x5+x1*x2*nx3*x4)
    # print(mdnf)

